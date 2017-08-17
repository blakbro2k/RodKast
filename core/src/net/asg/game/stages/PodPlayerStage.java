package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.List;

/**
 * Created by Blakbro2k on 7/29/2017.
 */

public class PodPlayerStage extends RodkastStageAdapter {
    private static final int COLSPAN = 2;

    public PodPlayerStage(RodKastApplication app) {
        super(app);

        Table main = new Table();
        main.setFillParent(true);
        main.top();
        main.debug();

        setUpStageTitleWindow(main, true);
        //setUpPlayerWindow(main, episodeList);
        //setUpPlayListWindow(main, episodeList);
        //setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpAdMobWindow(Table main) {
        app.getGameEvenListener().showBannerAd();

        Label nameLabel = new Label(MessageCatalog.ADMOB_WINDOW_MSG, defaultScreenLabelStyle);

        main.row();
        main.add(nameLabel).height(BANNER_SIZE).colspan(COLSPAN);
    }
}
