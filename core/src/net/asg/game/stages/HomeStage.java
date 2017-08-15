package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.PlayListWidget;
import net.asg.game.screens.PlayListScreen;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.List;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends RodkastStageAdapter {
    //TODO: Social Medial
    private static final float SOCIAL_WINDOW_SIZE = .4f;
    private static final float PLAYER_WINDOW_SIZE = .6f;
    private static final int COLUMN_SPAN = 2;

    public HomeStage(RodKastApplication app){
        super(app);



        Table main = new Table();
        main.setFillParent(true);
        main.debug();

        loadAssets();

        setUpStageTitleWindow(main);
        setUpPlayerWindow(main);
        setUpSocialWindow(main);
        setUpAdMobWindow(main);


        addActor(main);
        setInputProcessor();
    }

    private void setUpStageTitleWindow(Table main){
        main.row().expand();

        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, titleScreenLabelStyle);
        nameLabel.setSize(GlobalConstants.VIEWPORT_WIDTH,GlobalConstants.VIEWPORT_HEIGHT);
        main.add(nameLabel);
    }

    private void setUpAdMobWindow(Table main) {
        main.row();//.height(BANNER_SIZE);

        Label nameLabel = new Label(MessageCatalog.ADMOB_WINDOW_MSG, defaultScreenLabelStyle);
        app.getGameEvenListener().showBannerAd();
        main.add(nameLabel);
    }

    private void setUpPlayerWindow(Table main) {
        main.row().height(getBannerOffSet() * PLAYER_WINDOW_SIZE);

        Label nameLabel = new Label("Player Window Section", defaultScreenLabelStyle);
        //nameLabel.setFillParent(false);
        ImageButton playerButton = new ImageButton(imageProvider.getRodKastButtonStyle());
        //playerButton.setWidth(GlobalConstants.APP_WIDTH);

        playerButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                app.pushScreen(app.getCurrentScreen());
                app.gotoPlayListScreen();
            }
        });
        main.add(nameLabel);
    }

    private void setUpSocialWindow(Table main) {
        main.row().height(getBannerOffSet() * SOCIAL_WINDOW_SIZE);

        Label nameLabel = new Label(MessageCatalog.SOCIAL_WINDOW_MSG, defaultScreenLabelStyle);
        main.add(nameLabel);
    }
}
