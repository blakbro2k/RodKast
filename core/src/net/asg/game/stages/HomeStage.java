package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.BackButton;
import net.asg.game.menu.SettingsButton;
import net.asg.game.utils.GlobalConstants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends RodkastStageAdapter {
    //TODO: callButton
    //TODO: shopButton
    //TODO: AD Resolver
    //TODO: Podcast Scroller
    //TODO: Social Medial
    //TODO: SettingButton

    //protected Skin defaultSkin;
    protected Label.LabelStyle homeScreenLabelStyle;

    public HomeStage(RodKastApplication app){
        super(app);
        loadAssets();

        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
        main.debug();

        setUpStageTitleWindow(main);
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
        Label nameLabel = new Label("Admob Window Section", homeScreenLabelStyle);
        app.getGameEvenListener().showBannerAd();
        main.row();
        main.add(nameLabel).expandX().height(BANNER_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        Label nameLabel = new Label("Player Window Section", homeScreenLabelStyle);

        ImageButton playerButton = new ImageButton(imageProvider.getRodKastButtonStyle());
        playerButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                app.pushScreen(app.getCurrentScreen());
                app.gotoPlayListScreen();
                //app.getGameEvenListener().appLog("HOMESCREEN", event + "");
            }
        });

        main.row();
        playerButton.setWidth(200);
        playerButton.setHeight(100);
        main.add(playerButton).height(getBannerOffSet() * .6f).colspan(4).expandX().expandY();
    }

    private void setUpSocialWindow(Table main) {
        Label nameLabel = new Label("Social Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).expandX().height(getBannerOffSet() * .4f).colspan(4);
    }
}
