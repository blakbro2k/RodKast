package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
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
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private static final int HOMESTAGE_TOP_MENU_HEIGHT = 100;
    private static final int HOMESTAGE_BOTTOM_MENU_HEIGHT = 100;

    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private OrthographicCamera camera;
    private RodKastApplication app;
    private XMLHandler handler;


    private ExitDialog exitDialog;
    private Skin homeScreenSkin;
    private Skin playListSkin;

    private Label.LabelStyle homeScreenLabelStyle;

    public PlayListStage(RodKastApplication app){
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        this.app = app;

        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();
        this.handler = app.getXMLHandler();


        imageProvider.pauseUntilLoaded();
        //homeScreenSkin = imageProvider.getShadeUISkin();
        //homeScreenLabelStyle = imageProvider.getDefaultLableStyle();
        playListSkin = new Skin(imageProvider.getAtlas());

        try {
            handler.parseFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Util.setUpCamera(camera);

        setUpStageTitle();
        
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
    }

    private void setUpStageTitle() {

    }
}
