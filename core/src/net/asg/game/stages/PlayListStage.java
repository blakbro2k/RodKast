package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.RodKastApplication;
import net.asg.game.ui.EpisodeUi;
import net.asg.game.ui.RadialDownloadButtonGroup;
import net.asg.game.utils.ErrorUtils;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.ArrayList;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class PlayListStage extends RodkastStageAdapter {
    private static final float PLAYER_WINDOW_SIZE = .2f;
    private static final float PLAYLIST_WINDOW_SIZE = .8f;
    private static final float PLAYLIST_PADDING = 4f;

    public PlayListStage(RodKastApplication app){
        super(app);

        try{
            Table main = new Table();
            //main.setFillParent(true);
            main.top();
            main.debugAll();
            main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);
            main.setWidth(GlobalConstants.VIEWPORT_WIDTH);

            setUpStageTitleWindow(main, true);
            setUpPlayerWindow(main);
            setUpPlayListWindow(main);
            setUpAdMobWindow(main);

            addActor(main);
            setInputProcessor();
        } catch (Exception e){
            ErrorUtils.getInstance().showErrorPopup(e);
        }

    }

    private void setUpPlayListWindow(Table main) throws Exception{
        Actor playList = setUpPlayListActor(getEpisodeList());

        ScrollPane pane = new ScrollPane(playList);
        pane.setScrollingDisabled(true, false);

        main.row();
        main.add(pane).height(getBannerOffSet() * PLAYLIST_WINDOW_SIZE).colspan(COLSPAN);
    }

    private void setUpPlayerWindow(Table main) throws Exception{
        main.row();
        main.add(getMusicWidget()).height(getBannerOffSet() * PLAYER_WINDOW_SIZE).colspan(COLSPAN);
    }

    private Actor setUpPlayListActor(Array<RodkastEpisode> episodes) throws Exception{
        Table playList = new Table();
        playList.debugAll();

        episodes.iterator();
        for(final RodkastEpisode episode : episodes)
            if (episode != null) {

                EpisodeUi widget = new EpisodeUi(episode, defaultSkin);
                widget.debugAll();

                widget.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        _episodePlayer.setEpisode(episode);
                    }
                });

                RadialDownloadButtonGroup progressGroup = widget.getDownloadActor();

                //progressGroup.setValue(AudioUtils.getInstance().getAudioDownloadProgressValue(episode));
                //progressGroup

                //list.add(widget);

                playList.add(widget.getDateActor()).expand().height(EpisodeUi.DEFAULT_DATE_HEIGHT).width(EpisodeUi.DEFAULT_DATE_WIDTH);
                playList.add(widget.getTitleActor()).fill();
                playList.add(widget.getDownloadActor()).height(EpisodeUi.DEFAULT_DATE_HEIGHT).width(EpisodeUi.DEFAULT_DATE_WIDTH).expand().fill();
                playList.row();
            }

        return playList;
    }
}