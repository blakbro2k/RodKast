package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.BackButton;
import net.asg.game.utils.GlobalConstants;
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
    //protected Skin defaultSkin;
    protected Label.LabelStyle homeScreenLabelStyle;

    public PlayListStage(RodKastApplication app){
        super(app);

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
        main.add(nameLabel).expandX().height(BANNER_SIZE).colspan(4);
    }

    private void setUpPlayListWindow(Table main) {
        Actor playList = setUpPlayListActor(getEpisodelist());

        ScrollPane pane = new ScrollPane(playList);
        main.row();
        main.add(pane).expandX().height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        Label nameLabel = new Label("Player Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).expandX().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(4);
    }

    private Actor setUpPlayListActor(List<RodkastEpisode> episodes) {
        Table playList = new Table();

        for(RodkastEpisode episode : episodes){
            if(episode != null){
                Rectangle settingsButtonSound = new Rectangle(0, 0, 20, 20);

                BackButton backButton = new BackButton(settingsButtonSound, defaultSkin, new BackButtonListener());

                playList.add(createDateActor(episode)).center().pad(2f);
                playList.add(createTitleActor(episode)).left().pad(2f);
                playList.add(backButton).left();
                playList.row();
            }
        }

        return playList;
    }

    private Actor createTitleActor(RodkastEpisode episode) {
        if(episode == null){
            return null;
        }

        Label titleLabel = new Label(Utils.cleanTitle(episode.getTitle()), homeScreenLabelStyle);
        return new Container<Label>(titleLabel).fill();
    }

    private Actor createDateActor(RodkastEpisode episode) {
        if(episode == null){
            return null;
        }

        Calendar pubDate = episode.getPubishedDate();

        Table table = new Table();
        Label monthLabel = new Label(Utils.getThreeLetterMonth(pubDate.get(Calendar.MONTH)), homeScreenLabelStyle);
        Label dayLabel = new Label(pubDate.get(Calendar.DAY_OF_MONTH) + "", homeScreenLabelStyle);

        table.add(monthLabel).center();
        table.row();
        table.add(dayLabel).center();
        return new Container<Table>(table);
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, homeScreenLabelStyle);
        Rectangle settingsButtonSound = new Rectangle(0, 0, BANNER_SIZE, BANNER_SIZE);

        BackButton backButton = new BackButton(settingsButtonSound, defaultSkin, new BackButtonListener());

        main.add(backButton);
        main.add(nameLabel).expandX().height(BANNER_SIZE);
    }

    // Set up button listeners
    private class BackButtonListener implements BackButton.BackButtonListener{
        @Override
        public void onBackPress() {
            Utils.backButton(app);
        }
    }
}
