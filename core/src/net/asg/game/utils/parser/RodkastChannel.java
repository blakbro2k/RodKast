package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
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

    public RodkastChannel(XmlReader.Element element) throws IllegalArgumentException, MalformedURLException, ParseException {
        if (element == null){
            throw new IllegalArgumentException("XML Element cannot be null");
        }
                this.title = RodkastItemModel.getRssTitle(element);
                this.link = RodkastItemModel.getRssUrl(element);
                this.lastBuildDate = RodkastItemModel.getLastBuildDate(element);
                this.language = RodkastItemModel.getRssLanguage(element);
                this.description = RodkastItemModel.getRssDescription(element);
                this.images = RodkastItemModel.getRssImages(element);
                this.episodes = RodkastItemModel.getCompleteEpisodesList(element);
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
    public Map<String,XMLImage> getImages() {
        return images; }

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

        if(lastBuildDate != null){
            lastBuildDate.clear();
            lastBuildDate = null;
        }

        language = null;

        if(episodes != null){
            episodes.clear();
            episodes = null;
        }

        if(images != null){
            images.clear();
            images = null;
        }
    }
}
