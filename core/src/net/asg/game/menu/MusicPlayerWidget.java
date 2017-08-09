package net.asg.game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.providers.MenuProvider;
import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

/**
 * Created by Blakbro2k on 8/7/2017.
 */

public class MusicPlayerWidget extends Table {
    private Label.LabelStyle labelStyle;
    private Button playButton;
    private Button pauseButton;
    private String title;
    private RodkastEpisode episode;
    private Image image;
    private Label titleActor;
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

        this.playButton = new Button(skin.get(MenuProvider.RIGHT_BUTTON, Button.ButtonStyle.class));
        this.pauseButton = new Button(skin.get(MenuProvider.LEFT_BUTTON, Button.ButtonStyle.class));

        this.title = getTitleFromEpisode(episode);
        this.labelStyle = skin.get(MenuProvider.LABEL_STYLE_DEFAULT, Label.LabelStyle.class);
        this.image = image;
        this.titleActor = getTitleActor();

        playButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                processEvent(MusicPlayerWidget.this, false);
            }
        });

        pauseButton.addListener(new ClickListener()
        {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                processEvent(MusicPlayerWidget.this, true);
            }
        });

        setPlayerTitle();
    }

    private void processEvent(MusicPlayerWidget widget, boolean isPause){
        if(widget != null) {
            System.out.println("Playing : " + widget.getEpisode());
            AudioUtils.getInstance().setEpisode(widget.getEpisode());

            if (!isPause) {
                //AudioUtils.getInstance().playMusic();
            } else {
                //AudioUtils.getInstance().pauseMusic();
            }
        }
    }

    private void setPlayerTitle() {
        //setDebug(true);
        reset();
        add(image).left().fill();
        add(titleActor).left().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING).fillX();
        add(playButton).left().fillX();
    }

    private String getTitleFromEpisode(RodkastEpisode episode) {
        String string = "";
        if(episode != null){
            string = Utils.cleanTitle(episode.getTitle());
        }
        return string;
    }

    public Label getTitleActor() {
        if(titleActor == null){
            titleActor = new Label(title, labelStyle);
        }

        return titleActor;
    }

    public RodkastEpisode getEpisode(){
        return episode;
    }

    public void setEpisode(RodkastEpisode episode){
        this.episode = episode;
        this.title = getTitleFromEpisode(episode);
        this.titleActor = null;
        this.titleActor = getTitleActor();

        setPlayerTitle();
    }
}
