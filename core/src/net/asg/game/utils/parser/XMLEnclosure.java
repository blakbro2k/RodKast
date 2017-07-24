package net.asg.game.utils.parser;

import java.net.URL;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class XMLEnclosure {
    private float length;
    private String type;
    private URL url;

    public XMLEnclosure(float length, String type, String url){
        this.length = length;
        this.type = type;

        try{
            this.url = new URL(url);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public float getLength() {
        return length;
    }

    public String getType() {
        return type;
    }

    public URL getUrl() {
        return url;
    }
}
