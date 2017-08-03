package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by eboateng on 7/19/2017.
 */

public class XMLHandler implements Disposable{
    protected URL urlLink;
    protected Element xmlElements;
    protected boolean isFeedFetched;

    public XMLHandler(){
        try {
            this.urlLink = new URL(RodkastItemModel.RODKAST_URL_STRING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private InputStream getXMLstream(){
        try {
            return urlLink.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getTotalRssFeed() throws IOException {
        InputStream inputStream = getXMLstream();
        if(inputStream != null){
            try {
                XmlReader reader = new XmlReader();
                xmlElements = reader.parse(inputStream);
                isFeedFetched = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                inputStream.close();
            }
        }
    }

    public RodkastChannel buildChannel(){
        try {
            if(!isFeedFetched){
                getTotalRssFeed();
            }

            Element elem = xmlElements.getChildByName(RodkastItemModel.RSS_CHANNEL);
            if(elem != null){
                //System.out.println(elem);
                //System.out.println(elem.getChildrenByName(RodkastItemModel.RSS_IMAGE));

                return new RodkastChannel(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void dispose() {
        //xmlElements.remove();
        urlLink = null;
        xmlElements = null;
    }
}
