package net.asg.game.utils.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by eboateng on 7/19/2017.
 */

public class XMLHandler {
    private URL urlLink;
    private InputStream inputStream;

    public XMLHandler(){
        try {
            this.urlLink = new URL(RodkastFeedModel.RODKAST_URL_STRING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private InputStream getXMLstream(){
        try {
            return inputStream = urlLink.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    public List<RssFeedModel> parseFeed() throws XmlPullParserException, IOException {
        return parseFeed(getXMLstream());
    }*/
/*
    public List<RssFeedModel> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        if(inputStream != null){
            try {
                XmlPullParser xmlPullParser = Xml.newPullParser();
                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                xmlPullParser.setInput(inputStream, null);

                xmlPullParser.nextTag();
                while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                    int eventType = xmlPullParser.getEventType();

                    String name = xmlPullParser.getName();
                    if(name == null)
                        continue;

                    if(eventType == XmlPullParser.END_TAG) {
                        if(name.equalsIgnoreCase("item")) {
                            isItem = false;
                        }
                        continue;
                    }

                    if (eventType == XmlPullParser.START_TAG) {
                        if(name.equalsIgnoreCase("item")) {
                            isItem = true;
                            continue;
                        }
                    }

                    Log.d("MainActivity", "Parsing name ==> " + name);
                    String result = "";
                    if (xmlPullParser.next() == XmlPullParser.TEXT) {
                        result = xmlPullParser.getText();
                        xmlPullParser.nextTag();
                    }

                    if (name.equalsIgnoreCase(RodkastFeedModel.RSS_TITLE)) {
                        title = result;
                    } else if (name.equalsIgnoreCase(RodkastFeedModel.RSS_LINK)) {
                        link = result;
                    } else if (name.equalsIgnoreCase(RodkastFeedModel.RSS_DESCRIPTION)) {
                        description = result;
                    }

                    if (title != null && link != null && description != null) {
                        if(isItem) {
                            RssFeedModel item = new RssFeedModel(title, link, description);
                            items.add(item);
                        }
                        else {
                            mFeedTitle = title;
                            mFeedLink = link;
                            mFeedDescription = description;
                        }

                        title = null;
                        link = null;
                        description = null;
                        isItem = false;
                    }
                }

                return items;
            } finally {
                inputStream.close();
            }
        }
        return null;
    }*/
}
