package net.asg.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.providers.MenuProvider;
import net.asg.game.utils.AudioUtils;
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
    public final static String PROGRESS_ACTOR_NAME = "radialProgressBar";
    public final static String DOWNLOAD_ACTOR_NAME = "download";

    private LabelStyle labelStyle;
    private RadialProgressBar downloadProgressBar;
    private Button downloadButtons;
    private RadialDownloadButtonGroup downloadButtonGroup;
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
        this.downloadProgressBar =  new RadialProgressBar(0, 1, true, skin);
        downloadProgressBar.setName(PROGRESS_ACTOR_NAME);
        this.downloadButtons = new Button(skin, DOWNLOAD_ACTOR_NAME);
        downloadButtons.setName(DOWNLOAD_ACTOR_NAME);
        //TODO: Setup a Runnable if download button is pressed and progress is not 100.  Check if value changed
        //fire even if so
        //TODO: capture change even from value change

        this.downloadButtonGroup = new RadialDownloadButtonGroup(downloadProgressBar, downloadButtons);
        this.episode = episode;

        initialize();
    }

    private void initialize(){
        reset();

        add(getDateActor()).expand().fill();
        add(getTitleActor()).pad(4,2,2,4);

        RadialDownloadButtonGroup downloadButtonActor = getDownloadActor();
        downloadButtonActor.setValue(.5f);//AudioUtils.getInstance().getAudioDownloadProgressValue(episode));
        downloadButtonActor.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        download(episode);
                    }
                });
        add(downloadButtonActor).expand();
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

    public RadialDownloadButtonGroup getDownloadActor(){
        return downloadButtonGroup;
    }

    public RadialProgressBar getDownloadProgressBar(){
        return downloadProgressBar;
    }

    public Button getDownloadButton(){
        return downloadButtons;
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

    public void download(RodkastEpisode episode) throws GdxRuntimeException {
        if(episode == null){
            throw new RuntimeException("No episode found");
        }
        System.out.println("progrest: " + AudioUtils.getInstance().getAudioDownloadProgressValue(episode));
        AudioUtils.getInstance().dowloadEpisode(episode);
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
