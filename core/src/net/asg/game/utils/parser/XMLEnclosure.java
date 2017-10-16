package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

public class XMLEnclosure {
    static final String LENGTH_ATTRIBUTE = "length";
    static final String TYPE_ATTRIBUTE = "type";
    static final String URL_ATTRIBUTE = "url";

    private float length;
    private String type;
    private URL url;

    public XMLEnclosure(float length, String type, String url) throws MalformedURLException, IllegalArgumentException {
        if(length < 0){
            throw new IllegalArgumentException("length attribute cannot be less than zero.");
        }

        if(type == null){
            throw new IllegalArgumentException("type attribute is missing or invalid.");
        }

        this.length = length;
        this.type = type;
        this.url = new URL(url);
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

    public static String[] getRequiredAttirbutes() {
        return new String[]{LENGTH_ATTRIBUTE, TYPE_ATTRIBUTE, URL_ATTRIBUTE};
    }
}

