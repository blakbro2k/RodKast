package net.asg.game.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.Calendar;

/**
 * Created by Blakbro2k on 8/7/2017.
 */

public class MusicPlayerWidget extends Table {
    private Label.LabelStyle labelStyle;
    private Button button;
    private String title;
    private RodkastEpisode episode;
    private Image image;
    private Label titleActor;
    private static final float PLAYLIST_PADDING = 4f;


    public MusicPlayerWidget(RodkastEpisode episode, Skin skin, Image image){
        this.button = new Button(skin.get("right", Button.ButtonStyle.class));
        this.title = getTitleFromEpisode(episode);
        this.labelStyle = skin.get("default", Label.LabelStyle.class);
        this.image = image;
        this.titleActor = getTitleActor();
        setPlayerTitle();
    }

    private void setPlayerTitle() {
        //setDebug(true);
        reset();
        add(image).fill();
        add(titleActor).left().padLeft(PLAYLIST_PADDING).padRight(PLAYLIST_PADDING);
        add(button).left();
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
