package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.SettingsButton;
import net.asg.game.utils.GlobalConstants;

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
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);

        setUpStageTitleWindow(main);
        //setUpPlayerWindow(main);
        //setUpPlayListWindow(main);
        //setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, homeScreenLabelStyle);
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
