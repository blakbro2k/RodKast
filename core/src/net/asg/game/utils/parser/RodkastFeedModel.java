package net.asg.game.utils.parser;

import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * Created by eboateng on 7/18/2017.
 */

public class RodkastFeedModel extends RssFeedModel {
    public static final String RODKAST_URL_STRING = "http://rodkast.com/feed/podcast/";
    public static final String RSS_TITLE = "TITLE";
    public static final String RSS_LINK = "LINK";
    public static final String RSS_PUBLISHED_DATE = "PUBDATE";
    public static final String RSS_COMMENTS = "COMMENTS";
    public static final String RSS_DESCRIPTION = "DESCRIPTION";
    public static final String RSS_CATEGORY = "CATEGORY";

    private String title;
    private URL link;
    private Date pubDate;
    private String comments;
    private String description;
    private String category;
    //private String title;

    public RodkastFeedModel(Map<String, Object> attributes){
        super(attributes);
    }

    @Override
    public void initialize(Map<String, Object> attributes) {
        if(attributes == null){
            throw new ExceptionInInitializerError("Attributes cannot be null");
        }

        for(String key : attributes.keySet()){
            if(key != null){
                Object obj = attributes.get(key);
                if(obj != null){
                    setRssAttributes(key, obj);
                }
            }
        }
    }

    private void setRssAttributes(String attr, Object obj){
        String temp = attr.toUpperCase();

        switch(temp){
            case RSS_TITLE:
                this.title = (String) obj;
                break;
            case RSS_LINK:
                this.link = (URL) obj;
                break;
            case RSS_PUBLISHED_DATE:
                this.pubDate = (Date) obj;
                break;
            case RSS_COMMENTS:
                this.comments = (String) obj;
                break;
            case RSS_DESCRIPTION:
                this.description = (String) obj;
                break;
            case RSS_CATEGORY:
                this.category = (String) obj;
                break;
            default:
        }
    }

    public String getTitle(){
        return this.title;
    }

    public String getLinkString(){
        return this.link.toString();
    }

    public URL getLinkURL(){
        return this.link;
    }

    public Date getPubDate(){
        return this.pubDate;
    }

    public String getComments(){
        return this.comments;
    }

    public String getDescription(){
        return this.description;
    }
}
