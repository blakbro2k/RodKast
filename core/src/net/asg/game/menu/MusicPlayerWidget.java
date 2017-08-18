package net.asg.game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
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

public class MusicPlayerWidget extends Container {
    private Label.LabelStyle labelStyle;
    private Button playButton;
    private RodkastEpisode episode;
    private Image image;
    private Table main;

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
            AudioUtils.getInstance().playEpisode(widget.getEpisode());
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

        setActor(main);
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

    public void download(RodkastEpisode episode) {
        if(episode == null){
            throw new RuntimeException("No episode found");
        }

        AudioUtils.getInstance().dowloadEpisode(episode);
    }
}
