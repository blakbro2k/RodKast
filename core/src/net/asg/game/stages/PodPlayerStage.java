package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

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

        setUpStageTitleWindow(main, true);
        setUpEpisodeWindow(main);
        setUpPlayerWindow(main);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpEpisodeWindow(Table main) {
        //Label nameLabel = new Label("Player Window Section", defaultScreenLabelStyle);
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
