package net.asg.game.utils.parser;

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

    public XMLImage(String title, String link, String url){
        this.title = title;

        try{
            this.link = new URL(link);
            this.url = new URL(url);
        } catch (Exception e){
            e.printStackTrace();
        }
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
}