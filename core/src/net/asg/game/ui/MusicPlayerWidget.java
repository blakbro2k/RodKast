package net.asg.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.providers.MenuProvider;
import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

/**
 * Created by Blakbro2k on 8/7/2017.
 */

public class MusicPlayerWidget extends Container {
    private LabelStyle labelStyle;
    private Button playButton;
    private RodkastEpisode episode;
    private Image image;
    private Table main;
    private ProgressBar seekerBar;
    private Table titleActor;

    private static final float PLAYLIST_PADDING = 4f;
    private EventListener listener;

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
        this.playButton = new Button(skin, MenuProvider.RIGHT_BUTTON);
        this.labelStyle = skin.get(MenuProvider.LABEL_STYLE_DEFAULT, Label.LabelStyle.class);
        this.image = image;
        this.seekerBar = new ProgressBar(0, 1, 0.1f, false, skin);

        playButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                processPlayEvent(MusicPlayerWidget.this);
            }
        });

        seekerBar.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                processSliderEvent(MusicPlayerWidget.this, actor);
            }
        });

        setPlayerTitle();
    }

    private void processPlayEvent(MusicPlayerWidget widget){
        if(widget != null) {
            AudioUtils.getInstance().playEpisode(widget.getEpisode());
        }
    }

    private void processSliderEvent(MusicPlayerWidget widget, Actor actor){
        /*
        if (!sliderUpdating && slider.isDragging()){
            music.setEpisodePosition((slider.getValue() / 100f) * songDuration);
        }*/
        System.out.println("actor: " + actor);
        if(widget != null){
            AudioUtils.getInstance().setEpisodePosition(widget.getEpisode());
        }
    }

    private void setPlayerTitle() {
        if(main == null){
            main = new Table();
        }

        main.reset();
        main.add(image).left().fill().width(70);
        main.add(getTitleActor()).fill().expand();
        main.add(playButton).right().fill().width(70);

        addListener(listener);

        setActor(main);
    }

    private Actor getTitleActor() {
        if(titleActor == null){
            titleActor = new Table();
            titleActor.add(new Label("RodKast", labelStyle)).expand().fill();
            titleActor.row();
            titleActor.add(new Label(Utils.getTitleFromEpisode(episode), labelStyle)).expand().fill();
            titleActor.row();
            titleActor.add(seekerBar).expandX().fill();
        }

        return titleActor;
    }

    public RodkastEpisode getEpisode(){
        return episode;
    }

    public void setEpisode(RodkastEpisode episode){
        this.episode = episode;
        titleActor.clear();
        this.titleActor = null;
        setPlayerTitle();
    }

    @Override
    public boolean addListener (EventListener listener) {
        if(listener != null && titleActor != null){
            this.listener = listener;
            return titleActor.addListener(this.listener);
        }
        return false;
    }
}
