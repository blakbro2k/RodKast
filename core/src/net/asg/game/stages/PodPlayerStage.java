package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.GlobalConstants;

/**
 * Created by Blakbro2k on 7/29/2017.
 */

public class PodPlayerStage extends RodkastStageAdapter {
    private static final float PLAYER_WINDOW_SIZE = .6f;
    private ProgressBar progressBar;

    public PodPlayerStage(RodKastApplication app) {
        super(app);

        Table main = new Table();
        main.setFillParent(true);
        main.top();
        main.debug();
        main.setSize(GlobalConstants.APP_WIDTH, GlobalConstants.APP_HEIGHT);

        setUpStageTitleWindow(main, true);
        setUpEpisodeWindow(main);
        setUpPlayerWindow(main);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpEpisodeWindow(Table main) {
        ImageButton playerButton = new ImageButton(imageProvider.getRodKastButtonStyle());

        main.row();
        main.add(playerButton).height(getBannerOffSet() * PLAYER_WINDOW_SIZE).fillX().colspan(COLSPAN);
        main.row();
        main.add(progressBar);
    }

    private void setUpPlayerWindow(Table main) {

    }

    private void getProgressBar(){
        //progressBar = AudioUtils.getInstance().
    }
}
