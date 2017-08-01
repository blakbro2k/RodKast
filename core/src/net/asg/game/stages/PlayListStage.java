package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.BackButton;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.XMLHandler;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        Label nameLabel = new Label("Playlist Window Section", homeScreenLabelStyle);

        Actor playList = setUpPlayListActor(getEpisodelist());

        ScrollPane pane = new ScrollPane(playList);
        main.row();
        main.add(pane).expandX().height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(4);

        //main.row();
        //main.add(nameLabel).expandX().height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main) {
        Label nameLabel = new Label("Player Window Section", homeScreenLabelStyle);
        main.row();
        main.add(nameLabel).expandX().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(4);
    }

    private Actor setUpPlayListActor(List<RodkastEpisode> episodes) {
        Table playList = new Table();
        playList.debug();

        //List<Actor> actors = new ArrayList<>();

        for(RodkastEpisode episode : episodes){
            if(episode != null){
                Actor actor = setUpPlayListGroup(episode);
                if(actor != null){
                    playList.add(actor).expandX();
                    playList.row();
                }
            }
        }

        return playList;
    }

    private Actor setUpPlayListGroup(RodkastEpisode episode) {
        if(episode == null){
            return null;
        }

        long pubDate = episode.getPubishedDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(pubDate);
        //date = new SimpleDateFormat("MMM DD", Locale.US).parse(pubDate);

        //pubDate.getTime();
        Label dateLabel = new Label(Utils.getThreeLetterMonth(calendar.get(Calendar.MONTH)) + "\n" + calendar.get(Calendar.DAY_OF_WEEK), homeScreenLabelStyle);
        //Label dateLabel = new Label(calendar.get(Calendar.MONTH) + "", homeScreenLabelStyle);
        Label titleLabel = new Label(episode.getTitle(), homeScreenLabelStyle);

        HorizontalGroup hGroup = new HorizontalGroup();
        //hGroup.debug();
        hGroup.addActor(dateLabel);
        hGroup.addActor(titleLabel);
        ///hGroup.addActor(new Label("H", homeScreenLabelStyle));
        System.out.println(episode);
        return hGroup;
        //episodes.add(hGroup);
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, homeScreenLabelStyle);
        Rectangle settingsButtonSound = new Rectangle(0, 0, BANNER_SIZE, BANNER_SIZE);

        BackButton backButton = new BackButton(settingsButtonSound, defaultSkin, new BackButtonListener());
        //BackButton.debug();
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
