package net.asg.game.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.providers.MenuProvider;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;

import java.util.Calendar;

/**
 * Created by Blakbro2k on 8/7/2017.
 */

public class PlayListWidget extends Table{
    private LabelStyle labelStyle;
    private Button button;
    private Calendar date;
    private String title;
    private RodkastEpisode episode;
    private Container dateActor;
    private Label titleActor;

    public PlayListWidget(RodkastEpisode episode, Skin skin, String labelStyle){
        super(skin);
        if(episode == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_RODKAST_EPISODE_MSG);
        }

        if(skin == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_SKIN_MSG);
        }

        if(labelStyle == null) {
            throw new IllegalArgumentException(MessageCatalog.NULL_LABEL_STLYE_MSG);
        }

        this.button = new Button(skin.get(MenuProvider.RIGHT_BUTTON, ButtonStyle.class));
        this.labelStyle = skin.get(labelStyle, LabelStyle.class);
        this.episode = episode;
        this.title = getTitleFromEpisode(episode);
        this.date = getDateFromDate(episode);

        add(getTitleActor()).fill();
        add(getDateActor()).fill();
        add(getButton()).fill();
    }

    public PlayListWidget(RodkastEpisode episode, Skin skin){
        this(episode, skin, MenuProvider.LABEL_STYLE_DEFAULT);
    }

    public void setEpisode(RodkastEpisode episode){
        this.episode = episode;
        this.title = null;
        this.date = null;

        this.title = getTitleFromEpisode(episode);
        this.date = getDateFromDate(episode);
    }

    public RodkastEpisode getEpisode(){
        return episode;
    }

    public Actor getTitleActor() {
        if(titleActor == null){
            titleActor = new Label(title, labelStyle);
        }

        if(getDebug()){
            return titleActor.debug();
        } else {
            return titleActor;
        }
    }

    public Actor getDateActor() {
        if(dateActor == null){
            dateActor = new Container<>(createMonthDay(date));
        }

        if(getDebug()){
            return dateActor.debug();
        } else {
            return dateActor;
        }
    }

    public Button getButton(){
        if(getDebug()){
            button.debug();
            return button;
        } else {
            return button;
        }
    }

    private Calendar getDateFromDate(RodkastEpisode episode) {
        Calendar calendar = null;
        if(episode != null){
            calendar = episode.getPubishedDate();
        }
        return calendar;
    }

    private String getTitleFromEpisode(RodkastEpisode episode) {
        String string = "";
        if(episode != null){
            string = Utils.cleanTitle(episode.getTitle());
        }
        return string;
    }

    private Table createMonthDay(Calendar pubDate) {
        Table table = new Table();
        if(pubDate != null){
            Label monthLabel = new Label(Utils.getThreeLetterMonth(pubDate.get(Calendar.MONTH)), labelStyle);
            Label dayLabel = new Label(pubDate.get(Calendar.DAY_OF_MONTH) + "", labelStyle);

            table.add(monthLabel).center();
            table.row();
            table.add(dayLabel).center();
        }
        return table;
    }

    @Override
    public boolean addListener (EventListener listener) {
        boolean success = false;

        if(dateActor != null){
            success = dateActor.addListener(listener);
        }

        if(titleActor != null){
            success = titleActor.addListener(listener);
        }
        return success;
    }
}
