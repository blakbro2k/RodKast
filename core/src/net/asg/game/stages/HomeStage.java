package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.ui.RadialProgressBar;
import net.asg.game.utils.MessageCatalog;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends RodkastStageAdapter {
    //TODO: Social Medial
    private static final float SOCIAL_WINDOW_SIZE = .4f;
    private static final float PLAYER_WINDOW_SIZE = .6f;

    public HomeStage(RodKastApplication app){
        super(app);

        Table main = new Table();
        main.setFillParent(true);
        main.top();
        main.debug();

        addActor(main);
        setInputProcessor();

        setUpStageTitleWindow(main, false);
        setUpPlayerWindow(main);
        setUpSocialWindow(main);
        setUpAdMobWindow(main);
    }

    private void setUpPlayerWindow(Table main) {
        //Label nameLabel = new Label("Player Window Section", defaultScreenLabelStyle);
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
        main.add(playerButton).height(getBannerOffSet() * PLAYER_WINDOW_SIZE).fillX().colspan(COLSPAN);
    }

    private void setUpSocialWindow(Table main) {
        Label nameLabel = new Label(MessageCatalog.SOCIAL_WINDOW_MSG, defaultScreenLabelStyle);

        main.row();
        main.add(nameLabel).height(getBannerOffSet() * SOCIAL_WINDOW_SIZE).fillX().colspan(COLSPAN);
    }
}
