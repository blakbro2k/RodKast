package net.asg.game.utils.parser;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eboateng on 8/3/2017.
 */

public class XMLImage {
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String LINK_ATTRIBUTE = "link";
    public static final String URL_ATTRIBUTE = "url";
    public static final String WIDTH_ATTRIBUTE = "width";
    public static final String HEIGHT_ATTRIBUTE = "height";

    private String title;
    private URL link;
    private URL url;

    public XMLImage(String title, String link, String url) throws IllegalArgumentException, MalformedURLException{
        if(title == null || link == null || url == null){
            throw new IllegalArgumentException("XMLImage title, link or url cannot be null.");
        }
        this.title = title;
        this.link = new URL(link);
        this.url = new URL(url);
    }

    public String getTitle() {
        return title;
    }

    public URL getLink() {
        return link;
    }

    public URL getUrl() {
        return url;
    }

    public static String[] getRequiredAttirbutes() {
        return new String[]{TITLE_ATTRIBUTE, LINK_ATTRIBUTE, URL_ATTRIBUTE};
    }
}