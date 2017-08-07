package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.Actors.PlayListActor;
import net.asg.game.RodKastApplication;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter{
    private static final float PLAYER_WINDOW_SIZE = .2f;
    private static final float PLAYLIST_WINDOW_SIZE = .8f;
    private static final float PLAYLIST_PADDING = 4f;

    public PlayListStage(RodKastApplication app){
        super(app);

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
        //main.debug();

        setUpStageTitleWindow(main);
        setUpPlayerWindow(main);
        setUpPlayListWindow(main);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpAdMobWindow(Table main) {
        Label nameLabel = new Label(MessageCatalog.ADMOB_WINDOW_MSG, defaultScreenLabelStyle);
        app.getGameEvenListener().showBannerAd();
        main.row();
        main.add(nameLabel).expandX().height(BANNER_SIZE).colspan(4);
    }

    private void setUpPlayListWindow(Table main) {
        Actor playList = setUpPlayListActor(getEpisodelist());

        ScrollPane pane = new ScrollPane(playList);
        main.row();
        main.add(pane).expandX().height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        Label nameLabel = new Label(MessageCatalog.PLAYER_WINDOW_MSG, defaultScreenLabelStyle);

        main.row();
        main.add(nameLabel).expandX().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(4);
    }

    private Actor setUpPlayListActor(List<RodkastEpisode> episodes) {
        Table playList = new Table();
        playList.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        //playList.debug();


        for(RodkastEpisode episode : episodes){
            if(episode != null){
                playList.add(createDateActor(episode)).center().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING);
                playList.add(createTitleActor(episode)).left().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING);
                playList.add(menuProvider.getRightButton()).left();
                playList.row();
            }
        }
        return playList;
    }

    private Actor createTitleActor(RodkastEpisode episode) {
        if(episode == null){
            return null;
        }

        Label titleLabel = new Label(Utils.cleanTitle(episode.getTitle()), defaultScreenLabelStyle);
        return new Container<>(titleLabel).fill();
    }

    private Actor createDateActor(RodkastEpisode episode) {
        Container container = null;
        if(episode != null){
            Calendar pubDate = episode.getPubishedDate();

            if(pubDate != null){
                container = new Container<>(createMonthDay(pubDate));
            }
        }
        return container;
    }

    private Table createMonthDay(Calendar pubDate) {
        Table table = new Table();
        if(pubDate != null){
            Label monthLabel = new Label(Utils.getThreeLetterMonth(pubDate.get(Calendar.MONTH)), defaultScreenLabelStyle);
            Label dayLabel = new Label(pubDate.get(Calendar.DAY_OF_MONTH) + "", defaultScreenLabelStyle);

            table.add(monthLabel).center();
            table.row();
            table.add(dayLabel).center();
        }
        return table;
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, titleScreenLabelStyle);

        Button backButton = menuProvider.getBackButton();
        backButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Utils.backButton(app);
            }
        });

        main.add(backButton).padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING);
        main.add(nameLabel).expandX().height(BANNER_SIZE);
    }
}
