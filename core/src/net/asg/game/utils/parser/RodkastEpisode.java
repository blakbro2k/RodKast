package net.asg.game.utils.parser;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;

import net.asg.game.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by Blakbro2k on 7/22/2017.
 */

public class RodkastEpisode implements Disposable{
    private String title;
    private URL webLink;
    private Calendar pubishedDate;
    private String guid;
    private String description;
    private String category;
    private Music episode;
    private XMLEnclosure enclosure;

    public RodkastEpisode(XmlReader.Element item) throws MalformedURLException {
            if (item != null){
                this.title = RodkastItemModel.getRssTitle(item);
                this.webLink = RodkastItemModel.getRssUrl(item);
                this.pubishedDate = RodkastItemModel.getRssPubDate(item);
                this.guid = RodkastItemModel.getRssGUID(item);
                this.description = RodkastItemModel.getRssDescription(item);
                this.category = RodkastItemModel.getRssCategory(item);
                this.enclosure = RodkastItemModel.getRssEnclosure(item);
            }
    }

    public String getTitle() {
        return title;
    }

    public URL getWebLink() {
        return webLink;
    }

    public Calendar getPubishedDate() {
        return pubishedDate;
    }

    public String getGuid() {
        return guid;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Music getEpisode() {
        return episode;
    }

    public URL getMediaLink() {
        return enclosure.getUrl();
    }

    public void getStream(){

    }

    public void downloadStream(){

    }

    @Override
    public void dispose() {
        title = null;
        webLink = null;
        pubishedDate.clear();
        pubishedDate = null;
        guid = null;
        description = null;
        category = null;
        episode = null;
        enclosure = null;
    }

    @Override
    public String toString(){
        return "[\"" + title + "\" - " + Utils.getThreeLetterMonth(pubishedDate.get(Calendar.MONTH))
                + "-" + pubishedDate.get(Calendar.DAY_OF_WEEK) + "]";
    }
}
