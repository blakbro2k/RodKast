package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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

    private InputStream getXMLstream() throws IOException {
        return urlLink.openConnection().getInputStream();
    }

    public void getTotalRssFeed() throws GdxRuntimeException {
            try {
                InputStream inputStream = getXMLstream();

                if(inputStream != null) {
                    XmlReader reader = new XmlReader();
                    xmlElements = reader.parse(inputStream);
                    isFeedFetched = true;
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            }
    }

    public boolean isFetched(){
        return isFeedFetched;
    }

    public RodkastChannel buildChannel() throws GdxRuntimeException {
            if(!isFeedFetched){
                getTotalRssFeed();
            }

            Element elem = xmlElements.getChildByName(RodkastItemModel.RSS_CHANNEL);
            if(elem != null){
                return new RodkastChannel(elem);
            }
        return null;
    }

    @Override
    public void dispose() {
        urlLink = null;
        xmlElements = null;
    }
}
