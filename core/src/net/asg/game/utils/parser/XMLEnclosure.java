package net.asg.game.utils.parser;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Blakbro2k on 7/23/2017.
 */

class XMLEnclosure {
    static final String LENGTH_ATTRIBUTE = "length";
    static final String TYPE_ATTRIBUTE = "type";
    static final String URL_ATTRIBUTE = "url";

    private float length;
    private String type;
    private URL url;

    public XMLEnclosure(float length, String type, String url) throws MalformedURLException {
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
}
