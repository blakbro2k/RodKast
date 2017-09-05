package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by Blakbro2k on 7/22/2017.
 */

public class RodkastChannel implements Disposable{
    private String title;
    private URL link;
    private String description;
    private Calendar lastBuildDate;
    private String language;
    private Array<RodkastEpisode> episodes;
    private Map<String,XMLImage> images;

    public RodkastChannel(XmlReader.Element elem) throws GdxRuntimeException{
        try{
            if (elem != null) {
                this.title = RodkastItemModel.getRssTitle(elem);
                this.link = RodkastItemModel.getRssUrl(elem);
                this.lastBuildDate = RodkastItemModel.getLastBuildDate(elem);
                this.language = RodkastItemModel.getRssLanguage(elem);
                this.description = RodkastItemModel.getRssDescription(elem);
                //this.images = RodkastItemModel.getRssImages(elem);
                this.episodes = RodkastItemModel.getCompleteEpisodesList(elem);
            }
        } catch (MalformedURLException | ParseException e){
            throw new GdxRuntimeException(e);
        }
    }

    public String getTitle(){
        return title;
    }
    public URL getLink(){
        return link;
    }
    public Calendar getLastBuildDate(){
        return lastBuildDate;
    }
    public String getLanguage(){
        return language;
    }
    public String getDescription(){
        return description;
    }
    public Array<RodkastEpisode> getEpisodes() {
        return episodes;
    }
    /*public String getTitle(){
        return title;
    }*/

    public String toString() {
        return "<Title: " + getTitle() + ">\n"
                + "Url: " + getLink() + "\n"
                + "Date: " + getLastBuildDate() + "\n"
                + "Language: " + getLanguage() + "\n"
                + "Description: " + getDescription() + "\n"
                + "episode: " + getEpisodes() + "\n"
                + "</Title>";
    }

    @Override
    public void dispose() {
        title = null;
        link  = null;
        description = null;
        lastBuildDate.clear();
        lastBuildDate = null;
        language = null;
        episodes.clear();
        episodes = null;
        //private Map<String,XMLImage> images;
    }
}
