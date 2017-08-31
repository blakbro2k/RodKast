package net.asg.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
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
    public final static int DEFAULT_DATE_WIDTH = 50;
    public final static int DEFAULT_DATE_HEIGHT = 50;
    public final static int DEFAULT_PADDING = 550;

    private LabelStyle labelStyle;
    private RadialProgressBar downloadButton;
    private RodkastEpisode episode;
    private Table dateActor;
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

        this.labelStyle = skin.get(labelStyle, LabelStyle.class);
        this.downloadButton =  new RadialProgressBar(0, 1, true, skin, "download");
        this.episode = episode;

        initialize();
    }

    private void initialize(){
        reset();

        add(getDateActor()).expand().fill();
        add(getTitleActor()).pad(4,2,2,4);
        add(getDownloadButton());
    }

    public PlayListWidget(RodkastEpisode episode, Skin skin){
        this(episode, skin, MenuProvider.LABEL_STYLE_DEFAULT);
    }

    public void setEpisode(RodkastEpisode episode){
        this.episode = episode;
        initialize();
    }

    public RodkastEpisode getEpisode(){
        return episode;
    }

    public Actor getTitleActor() {
        if(episode != null){
            if(titleActor == null){
                titleActor = new Label(Utils.getTitleFromEpisode(episode), labelStyle);
            }

            return titleActor;
        }
        return new Label("Title Not Loaded", labelStyle);
    }

    public Actor getDateActor() {
        if(dateActor == null){
            Table date = new Table();
            date.add(createMonthDay(getDateFromDate())).fill().expand();
            dateActor = date;
        }

        return dateActor;
    }

    public RadialProgressBar getDownloadButton(){
        return downloadButton;
    }

    private Calendar getDateFromDate() {
        Calendar calendar = null;
        if(episode != null){
            calendar = episode.getPubishedDate();
        }
        return calendar;
    }

    private Table createMonthDay(Calendar pubDate) {
        Table mTable = new Table();
        //mTable.top();

        if(pubDate != null){
            Label monthLabel = new Label(Utils.getThreeLetterMonth(pubDate.get(Calendar.MONTH)), labelStyle);
            Label dayLabel = new Label(pubDate.get(Calendar.DAY_OF_MONTH) + "", labelStyle);

            mTable.add(monthLabel).center().expand();
            mTable.row();
            mTable.add(dayLabel).center().expand();
        }

        if(getDebug()){
            mTable.debugAll();
        }

        return mTable;
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
