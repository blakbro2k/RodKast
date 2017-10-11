package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

/**
 * Created by eboateng on 7/19/2017.
 */

public class XMLHandler implements Disposable{
    private URL urlLink;
    private Element xmlElements;
    private boolean isFeedFetched;

    public XMLHandler() throws MalformedURLException {
            this.urlLink = new URL(RodkastItemModel.RODKAST_URL_STRING);
    }

    InputStream getXMLstream() throws IOException {
        return urlLink.openConnection().getInputStream();
    }

    private void fetchFeed() throws IOException {
        InputStream inputStream = getXMLstream();

        if(inputStream != null) {
            XmlReader reader = new XmlReader();
            xmlElements = reader.parse(inputStream);
            isFeedFetched = true;
            inputStream.close();
        }
    }

    public Element getRssFeed() throws IOException {
        if(!isFeedFetched){
            fetchFeed();
        }
        return xmlElements;
    }

    public boolean isFetched(){
        return isFeedFetched;
    }

    public RodkastChannel buildChannel() throws IOException, ParseException {
        Element elem = getRssFeed();

        Element channel = elem.getChildByName(RodkastItemModel.RSS_CHANNEL);

        if(channel != null){
            return new RodkastChannel(channel);
        }
        return null;
    }

    @Override
    public void dispose() {
        urlLink = null;
        xmlElements = null;
    }
}
