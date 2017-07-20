package net.asg.game.utils.parser;

import java.util.Date;

/**
 * Created by eboateng on 7/17/2017.
 */

public class PodcastItem {
    private String title;
    private String link;
    private Date pubDate;
    private String gUID;
    private String wfwComent;
    private String slashComment;
    private String category;
    private String description;
    private String contentDescription;
    private Object enclosure;
    private String iosSubtitle;
    private String iosSummary;
    private String iosImageLink;
    private boolean explicitContent;

    public PodcastItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getgUID() {
        return gUID;
    }

    public void setgUID(String gUID) {
        this.gUID = gUID;
    }
}
