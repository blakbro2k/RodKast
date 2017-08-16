package net.asg.game.menu;

import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.providers.MenuProvider;
import net.asg.game.stages.RodkastStageAdapter;
import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.net.URL;

/**
 * Created by Blakbro2k on 8/7/2017.
 */

public class MusicPlayerWidget extends Table {
    private Label.LabelStyle labelStyle;
    private Button playButton;
    private RodkastEpisode episode;
    private Image image;

    private static final float PLAYLIST_PADDING = 4f;

    public MusicPlayerWidget(RodkastEpisode episode, Skin skin, Image image){
        if(episode == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_RODKAST_EPISODE_MSG);
        }

        if(skin == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_SKIN_MSG);
        }

        if(image == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_IMAGE_MSG);
        }

        this.episode = episode;
        this.playButton = new Button(skin.get(MenuProvider.RIGHT_BUTTON, Button.ButtonStyle.class));
        this.labelStyle = skin.get(MenuProvider.LABEL_STYLE_DEFAULT, Label.LabelStyle.class);
        this.image = image;

        playButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                processEvent(MusicPlayerWidget.this);
            }
        });

        setPlayerTitle();
    }

    private void processEvent(MusicPlayerWidget widget){
        if(widget != null) {
            System.out.println("Setting : " + widget.getEpisode());
            AudioUtils.getInstance().setEpisode(widget.getEpisode());
            AudioUtils.getInstance().playMusic();

            //boolean isPaused = AudioUtils.getInstance().isPaused();

            //if (!isPaused) {
            //} else {
            //    AudioUtils.getInstance().pauseMusic();
            //}
        }
    }

    private void setPlayerTitle() {
        if(getDebug()) {
            setDebug(true);
        }

        reset();
        add(image).left();
        add(getTitleActor());
        add(playButton);
    }

    private Label getTitleActor() {
        return new Label(Utils.getTitleFromEpisode(episode), labelStyle);
    }

    public RodkastEpisode getEpisode(){
        return episode;
    }

    public void setEpisode(RodkastEpisode episode){
        this.episode = episode;
        setPlayerTitle();
    }

    public MusicPlayerWidget getInstance(){
        return this;
    }

    public void download(RodkastStageAdapter stage, RodkastEpisode episode) {
        if(stage == null){
            throw new RuntimeException("Stage cannot be null.");
        }

        if(episode == null){
            throw new RuntimeException("No episode found");
        }

        AudioUtils.getInstance().dowloadEpisode(stage, episode);
    }
}
