package net.asg.game.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import net.asg.game.RodKastApplication;
import net.asg.game.ui.MusicPlayerWidget;
import net.asg.game.ui.PlayListWidget;
import net.asg.game.ui.RadialDownloadButtonGroup;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.parser.RodkastEpisode;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter {
    private static final float PLAYER_WINDOW_SIZE = .2f;
    private static final float PLAYLIST_WINDOW_SIZE = .8f;
    private static final float PLAYLIST_PADDING = 4f;
    private static final String MUSICPLAYER_NAME = "musicplayer";
    private MusicPlayerWidget _episodePlayer;

    public PlayListStage(RodKastApplication app){
        super(app);

        Table main = new Table();
        main.setFillParent(true);
        main.top();
        main.debugAll();

        Array<RodkastEpisode> episodeList = getEpisodelist();

        setUpStageTitleWindow(main, true);
        setUpPlayerWindow(main, episodeList);
        setUpPlayListWindow(main, episodeList);
        setUpAdMobWindow(main);

        addActor(main);
        setInputProcessor();
    }

    private void setUpPlayListWindow(Table main, Array<RodkastEpisode> episodeList) {
        Actor playList = setUpPlayListActor(episodeList);

        ScrollPane pane = new ScrollPane(playList);

        main.row();
        main.add(pane).height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(COLSPAN);
    }

    private void setUpPlayerWindow(Table main, Array<RodkastEpisode> episodeList) {
        RodkastEpisode defaultEpisode = null; //TODO: Add getLastPlayed Preference to AudioUtils

        if(episodeList != null) {
            defaultEpisode = episodeList.get(0);
        }

        TextureRegion rodKastTextureRegion = imageProvider.getRodKastImage();
        Image rodKastImage = new Image(rodKastTextureRegion);

        _episodePlayer = new MusicPlayerWidget(defaultEpisode, defaultSkin, rodKastImage);
        _episodePlayer.setName(MUSICPLAYER_NAME);
        _episodePlayer.debugAll();
        _episodePlayer.size(GlobalConstants.APP_WIDTH, getBannerOffSet() * PLAYER_WINDOW_SIZE);
        _episodePlayer.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                app.pushScreen(app.getCurrentScreen());
                app.gotoPodPlayerScreen();
            }
        });

        main.row();
        main.add(_episodePlayer).height(getBannerOffSet() * PLAYER_WINDOW_SIZE).fillX().expandX().colspan(COLSPAN);
    }

    private Actor setUpPlayListActor(Array<RodkastEpisode> episodes) {
        Table playList = new Table();
        playList.debugAll();

        //Array<PlayListWidget> pList = new Array<>();

        for(final RodkastEpisode episode : episodes)
            if (episode != null) {

                PlayListWidget widget = new PlayListWidget(episode, defaultSkin);
                widget.debugAll();

                //final Utils.EpisodeEncapsulation epCap = new Utils.EpisodeEncapsulation(episode, widget.getDownloadProgressBar());

                widget.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        _episodePlayer.setEpisode(episode);
                    }
                });

                RadialDownloadButtonGroup progressGroup = widget.getDownloadActor();

                //progressGroup.setValue(AudioUtils.getInstance().getAudioDownloadProgressValue(episode));
                //progressGroup

                //pList.add(widget);

                playList.add(widget.getDateActor()).expand().height(PlayListWidget.DEFAULT_DATE_HEIGHT).width(PlayListWidget.DEFAULT_DATE_WIDTH);
                playList.add(widget.getTitleActor()).fill();
                playList.add(widget.getDownloadActor()).height(PlayListWidget.DEFAULT_DATE_HEIGHT).width(PlayListWidget.DEFAULT_DATE_WIDTH).expand().fill();
                playList.row();
            }
        //List<Table> uiList = new List<Table>(defaultSkin);
        //uiList.setItems(pList);
        return playList;
    }
}