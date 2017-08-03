package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.GlobalConstants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends RodkastStageAdapter {
    //TODO: callButton
    //TODO: shopButton
    //TODO: Social Medial

    private static final float SOCIAL_WINDOW_SIZE = .4f;
    private static final float PLAYER_WINDOW_SIZE = .6f;

    protected Label.LabelStyle homeScreenLabelStyle;

    public HomeStage(RodKastApplication app){
        super(app);

        homeScreenLabelStyle = menuProvider.getTitleLableStyle();

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
        //main.debug();

        setUpStageTitleWindow(main);
        loadAssets();

        setUpPlayerWindow(main);
        setUpSocialWindow(main);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();

    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, homeScreenLabelStyle);
        main.add(nameLabel).expandX().height(BANNER_SIZE);
    }

    private void setUpAdMobWindow(Table main) {
        Label nameLabel = new Label("Admob Could not load", homeScreenLabelStyle);
        app.getGameEvenListener().showBannerAd();
        main.row();
        main.add(nameLabel).fill().height(BANNER_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        //Label nameLabel = new Label("Player Window Section", homeScreenLabelStyle);

        ImageButton playerButton = new ImageButton(imageProvider.getRodKastButtonStyle());
        playerButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                app.pushScreen(app.getCurrentScreen());
                app.gotoPlayListScreen();
            }
        });

        main.row();
        main.add(playerButton).fill().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(4);
    }

    private void setUpSocialWindow(Table main) {
        Label nameLabel = new Label("Social Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).fill().height(getBannerOffSet() * SOCIAL_WINDOW_SIZE).colspan(4);
    }
}
