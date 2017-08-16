package net.asg.game.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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

public class PlayListStage extends RodkastStageAdapter {
    private static final float PLAYER_WINDOW_SIZE = .2f;
    private static final float PLAYLIST_WINDOW_SIZE = .8f;
    private static final int COLSPAN = 2;
    private static final float PLAYLIST_PADDING = 4f;
    private static final String MUSICPLAYER_NAME = "musicplayer";
    private MusicPlayerWidget _episodePlayer;

    public PlayListStage(RodKastApplication app){
        super(app);

        Table main = new Table();
        main.setFillParent(true);
        main.top();
        main.debug();

        List<RodkastEpisode> episodeList = getEpisodelist();

        setUpStageTitleWindow(main);
        setUpPlayerWindow(main, episodeList);
        setUpPlayListWindow(main, episodeList);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpAdMobWindow(Table main) {
        app.getGameEvenListener().showBannerAd();

        Label nameLabel = new Label(MessageCatalog.ADMOB_WINDOW_MSG, defaultScreenLabelStyle);

        main.row();
        main.add(nameLabel).height(BANNER_SIZE).colspan(COLSPAN);
    }

    private void setUpPlayListWindow(Table main, List<RodkastEpisode> episodeList) {
        Label paneLabel = new Label(MessageCatalog.ADMOB_WINDOW_MSG, defaultScreenLabelStyle);
        Actor playList = setUpPlayListActor(episodeList);

        ScrollPane pane = new ScrollPane(playList);

        main.row();
        main.add(pane).height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(COLSPAN);
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
        _episodePlayer.debug();
        _episodePlayer.setName(MUSICPLAYER_NAME);
        //_episodePlayer.setEpisode(defaultEpisode);
        //_episodePlayer.setWidth(GlobalConstants.VIEWPORT_WIDTH);

        main.row();
        main.add(_episodePlayer).left().height(getBannerOffSet() * PLAYER_WINDOW_SIZE).fillX().expandX().colspan(COLSPAN);
    }

    private Actor setUpPlayListActor(List<RodkastEpisode> episodes) {
        Table playList = new Table();
        playList.debug();

        for(final RodkastEpisode episode : episodes){
            if(episode != null){

                PlayListWidget widget = new PlayListWidget(episode, defaultSkin);
                widget.debug();

                widget.addListener(new ClickListener()
                {
                    @Override
                    public void clicked (InputEvent event, float x, float y) {
                        _episodePlayer.setEpisode(episode);
                    }
                });

                Button button = widget.getButton();
                button.addListener(new ClickListener()
                {
                    @Override
                    public void clicked (InputEvent event, float x, float y) {
                        _episodePlayer.download(PlayListStage.this, episode);
                    }
                });

                playList.add(widget.getDateActor()).expand().height(PlayListWidget.DEFAULT_DATE_HEIGHT)
                        .width(PlayListWidget.DEFAULT_DATE_WIDTH);
                playList.add(widget.getTitleActor()).fill();
                playList.add(widget.getButton()).height(PlayListWidget.DEFAULT_DATE_HEIGHT)
                        .width(PlayListWidget.DEFAULT_DATE_WIDTH);
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

        main.add(backButton).height(BANNER_SIZE).left().width(backButton.getWidth()).fill();
        main.add(nameLabel).height(BANNER_SIZE).left().expandX();
    }
}
