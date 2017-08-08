package net.asg.game.stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.MusicPlayerWidget;
import net.asg.game.menu.PlayListWidget;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.List;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter{
    private static final float PLAYER_WINDOW_SIZE = .2f;
    private static final float PLAYLIST_WINDOW_SIZE = .8f;
    private static final float PLAYLIST_PADDING = 4f;
    private static final String MUSICPLAYER_NAME = "musicplayer";
    private MusicPlayerWidget _episodePlayer;

    public PlayListStage(RodKastApplication app){
        super(app);

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
        List<RodkastEpisode> episodeList = getEpisodelist();
        //main.debug();

        setUpStageTitleWindow(main);
        setUpPlayerWindow(main, episodeList);
        setUpPlayListWindow(main, episodeList);
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

    private void setUpPlayListWindow(Table main, List<RodkastEpisode> episodeList) {
        Actor playList = setUpPlayListActor(episodeList);

        ScrollPane pane = new ScrollPane(playList);
        main.row();
        main.add(pane).expandX().height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(4);
    }

    private void setUpPlayerWindow(Table main, List<RodkastEpisode> episodeList) {
        Label nameLabel = new Label(MessageCatalog.PLAYER_WINDOW_MSG, defaultScreenLabelStyle);

        RodkastEpisode defaultEpisode = null;

        if(episodeList != null) {
            defaultEpisode = episodeList.get(0);
        }

        TextureRegion rodKastTextureRegion = imageProvider.getRodKastImage();
        Image rodKastImage = new Image(rodKastTextureRegion);

        _episodePlayer = new MusicPlayerWidget(defaultEpisode, defaultSkin, rodKastImage);
        //episodePlayer.debug();
        _episodePlayer.setName(MUSICPLAYER_NAME);
        _episodePlayer.setEpisode(defaultEpisode);

        main.debug();
        main.row();
        //main.add(nameLabel).expandX().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(4);
        main.add(_episodePlayer).fill().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(3);
    }

    private Actor setUpPlayListActor(List<RodkastEpisode> episodes) {
        Table playList = new Table();
        playList.setWidth(GlobalConstants.VIEWPORT_WIDTH);

        for(RodkastEpisode episode : episodes){
            if(episode != null){

                final PlayListWidget widget = new PlayListWidget(episode, defaultSkin);
                widget.addListener(new ClickListener()
                {
                    @Override
                    public void clicked (InputEvent event, float x, float y) {
                        _episodePlayer.setEpisode(widget.getEpisode());
                    }
                });

                Button button = widget.getButton();
                button.addListener(new ClickListener()
                {
                    @Override
                    public void clicked (InputEvent event, float x, float y) {
                        System.out.println("downloading " + widget.getEpisode() + "...");
                    }
                });

                //widget.debug();
                playList.add(widget.getDateActor()).center().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING).fill();
                playList.add(widget.getTitleActor()).left().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING).fill();
                playList.add(widget.getButton()).left().fillX();
                playList.row();
            }
        }
        return playList;
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
        main.add(nameLabel).expandX().height(BANNER_SIZE).left();
    }
}
