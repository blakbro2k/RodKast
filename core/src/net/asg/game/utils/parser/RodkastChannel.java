package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
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
    private List<RodkastEpisode> episodes;
    private Map<String,XMLImage> images;

    public RodkastChannel(XmlReader.Element elem) throws MalformedURLException {
        if (elem != null) {
            this.title = RodkastItemModel.getRssTitle(elem);
            this.link = RodkastItemModel.getRssUrl(elem);
            this.lastBuildDate = RodkastItemModel.getLastBuildDate(elem);
            this.language = RodkastItemModel.getRssLanguage(elem);
            this.description = RodkastItemModel.getRssDescription(elem);
            //this.images = RodkastItemModel.getRssImages(elem);
            this.episodes = RodkastItemModel.getCompleteEpisodesList(elem);
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
    public List<RodkastEpisode> getEpisodes() {
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
