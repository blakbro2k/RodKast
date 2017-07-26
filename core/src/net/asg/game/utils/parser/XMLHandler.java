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
        return null;
    }

    public List<RodkastEpisode> getEpisodes() throws MalformedURLException {
        Element elem;

        if(isFeedFetched){
            elem = xmlElements.getChildByName(RodkastItemModel.RSS_CHANNEL);
            if(elem != null){
                Array<Element> items = elem.getChildrenByName(RodkastItemModel.RSS_ITEM);
                return buildRodkestEpisodes(items);
            }
        }
        return null;
    }



    private List<RodkastEpisode> buildRodkestEpisodes(Array<Element> items) throws MalformedURLException {
        List<RodkastEpisode> episodes = null;

        if(items != null){
            episodes = new ArrayList<>();
            for(Element item : items){
                if(item != null){
                    episodes.add(new RodkastEpisode(item));
                }
            }
        }
        return episodes;
    }

    @Override
    public void dispose() {
        //xmlElements.remove();
        urlLink = null;
        xmlElements = null;
    }
}
