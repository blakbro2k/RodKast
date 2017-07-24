package net.asg.game.utils.parser;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Blakbro2k on 7/22/2017.
 */

public class RodkastChannel {
    private String title;
    private URL link;
    private String description;
    private Date lastBuildDate;
    private String language;
    private List<RodkastEpisode> episodes;


    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }


}
