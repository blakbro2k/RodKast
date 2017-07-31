package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.BackButton;
import net.asg.game.menu.SettingsButton;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter{
    //protected Skin defaultSkin;
    protected Label.LabelStyle homeScreenLabelStyle;

    public PlayListStage(RodKastApplication app){
        super(app);

        //defaultSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
        main.debug();

        setUpStageTitleWindow(main);
        setUpPlayerWindow(main);
        setUpPlayListWindow(main);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpAdMobWindow(Table main) {
        Label nameLabel = new Label("Admob Window Section", homeScreenLabelStyle);
        app.getGameEvenListener().showBannerAd();
        main.row();
        main.add(nameLabel).expandX().height(GlobalConstants.VIEWPORT_WIDTH * .2f).colspan(4);
    }

    private void setUpPlayListWindow(Table main) {
        Label nameLabel = new Label("Playlist Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).expandX().height(GlobalConstants.VIEWPORT_WIDTH * .4f).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        Label nameLabel = new Label("Player Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).expandX().height(GlobalConstants.VIEWPORT_WIDTH * .2f).colspan(4);
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, homeScreenLabelStyle);
        Rectangle settingsButtonSound = new Rectangle(0, 0, 100, 100);

        BackButton backButton = new BackButton(settingsButtonSound, defaultSkin, new BackButtonListener());
        //BackButton.debug();
        main.add(backButton);
        main.add(nameLabel).expandX().height(GlobalConstants.VIEWPORT_WIDTH * .2f);
    }

    // Set up button listeners
    private class BackButtonListener implements BackButton.BackButtonListener{

        @Override
        public void onBackPress() {
            Utils.backButton(app);
        }
    }
}
