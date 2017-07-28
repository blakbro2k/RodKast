package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.SettingsButton;
import net.asg.game.utils.Constants;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter{
    protected Skin defaultSkin;
    protected Label.LabelStyle homeScreenLabelStyle;

    public PlayListStage(RodKastApplication app){
        super(app);

        defaultSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        Table main = new Table();
        main.setWidth(Constants.VIEWPORT_WIDTH);
        main.setHeight(Constants.VIEWPORT_HEIGHT);

        setUpStageTitle(main);
        addActor(main);

        setInputProcessor();
    }

    private void setUpStageTitle(Table main){
        Label nameLabel = new Label(Constants.GAME_TITLE, homeScreenLabelStyle);
        Rectangle settingsButtonSound = new Rectangle(0, 0, 100, 100);

        SettingsButton settingsButton = new SettingsButton(settingsButtonSound, defaultSkin, new SettingsButtonListener());
        settingsButton.debug();
        main.add(nameLabel);
    }

    // Set up button listeners
    private class SettingsButtonListener implements SettingsButton.SettingsButtonListener{

        @Override
        public void onSettings() {
            System.out.println("Settings Button Pressed");
        }
    }
}
