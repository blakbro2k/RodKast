package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.menu.SettingsButton;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;
import net.asg.game.utils.Util;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends Stage{
    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private RodKastApplication app;
    private OrthographicCamera camera;
    private XMLHandler xmlHandler;

    private ExitDialog exitDialog;
    private Skin playListSkin;

    private Label.LabelStyle homeScreenLabelStyle;

    public PlayListStage(RodKastApplication app){
        super(new ScalingViewport(Scaling.stretch, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,
                new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)));
        this.app = app;
        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();
        this.xmlHandler = app.getXMLHandler();


        imageProvider.pauseUntilLoaded();
        //homeScreenSkin = imageProvider.getShadeUISkin();
        //homeScreenLabelStyle = imageProvider.getDefaultLableStyle();
        playListSkin = new Skin(imageProvider.getAtlas());
        //homeScreenSkin = imageProvider.getShadeUISkin();

        try {
            xmlHandler.getTotalRssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Util.setUpCamera(camera);

        Table main = new Table();
        main.setWidth(Constants.VIEWPORT_WIDTH);
        main.setHeight(Constants.VIEWPORT_HEIGHT);

        setUpStageTitle(main);
        addActor(main);

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);

    }

    private void setUpStageTitle(Table main){
        Label nameLabel = new Label(Constants.GAME_TITLE, playListSkin);
        Rectangle settingsButtonSound = new Rectangle(0, 0, 100, 100);

        SettingsButton settingsButton = new SettingsButton(settingsButtonSound, playListSkin, new SettingsButtonListener());
        settingsButton.debug();
        main.add(nameLabel);
    }

    // Set up button listeners
    private class SettingsButtonListener implements SettingsButton.SettingsButtonListener{

        @Override
        public void onShare() {
            System.out.println("Settings Button Pressed");
        }
    }
}
