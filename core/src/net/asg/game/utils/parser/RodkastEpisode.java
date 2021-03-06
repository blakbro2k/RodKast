package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;

import net.asg.game.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
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
    private XMLEnclosure enclosure;

    public RodkastEpisode(XmlReader.Element element) throws IllegalArgumentException, MalformedURLException, ParseException {
        if (element == null){
            throw new IllegalArgumentException("XML Element cannot be null");
        }

                this.title = RodkastItemModel.getRssTitle(element);
                this.webLink = RodkastItemModel.getRssUrl(element);
                this.pubishedDate = RodkastItemModel.getRssPubDate(element);
                this.guid = RodkastItemModel.getRssGUID(element);
                this.description = RodkastItemModel.getRssDescription(element);
                this.category = RodkastItemModel.getRssCategory(element);
                this.enclosure = RodkastItemModel.getRssEnclosure(element);
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

    public URL getMediaLink() {
        return enclosure.getUrl();
    }

    public XMLEnclosure getEnclosure(){
        return enclosure;
    }

    public String getType() {
        return enclosure.getType();
    }

    public float getDuration() {
        return enclosure.getLength();
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
        enclosure = null;
    }

    @Override
    public String toString(){
        return "[\"" + title + "\" - " + Utils.getThreeLetterMonth(pubishedDate.get(Calendar.MONTH))
                + "-" + pubishedDate.get(Calendar.DAY_OF_MONTH) + "]";
    }
}
