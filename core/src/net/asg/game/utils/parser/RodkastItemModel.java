package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.asg.game.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by eboateng on 7/18/2017.
 */

class RodkastItemModel{
    public static final String RODKAST_URL_STRING = "http://rodkast.com/feed/podcast/";
    public static final String RSS_TITLE = "title";
    public static final String RSS_LINK = "link";
    public static final String RSS_PUBLISHED_DATE = "pubDate";
    public static final String RSS_LAST_BUILD_DATE = "lastBuildDate";
    public static final String RSS_ITEM = "item";
    public static final String RSS_DESCRIPTION = "description";
    public static final String RSS_CATEGORY = "category";
    public static final String RSS_GUID = "guid";
    public static final String RSS_LANGUAGE = "language";
    public static final String RSS_IMAGE = "image";
    public static final String RSS_EPISODE = "enclosure";
    public static final String RSS_CHANNEL = "channel";
    public static final String RSS_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";

    private RodkastItemModel(){}

    protected static String getRssTitle(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_TITLE);
        if(elem == null){
            throwArgumentException(RSS_TITLE);
        }
        return elem.getText();
    }

    public static URL getRssUrl(Element item) throws MalformedURLException, IllegalArgumentException {
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_LINK);
        if(elem == null){
            throwArgumentException(RSS_LINK);
        }
        return new URL(elem.getText());
    }

    public static Calendar getRssPubDate(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        return getXmlDateAttribute(RSS_PUBLISHED_DATE, item);
    }

    public static Calendar getLastBuildDate(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        return getXmlDateAttribute(RSS_LAST_BUILD_DATE, item);
    }

    private static Calendar getXmlDateAttribute(String attr, Element element){
        if(attr == null || element == null){
            return null;
        }

        Element elem = element.getChildByName(attr);
        if(elem == null){
            throwArgumentException(attr);
        }

        return parseDate(elem.getText());
    }

    public static String getRssGUID(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_GUID);
        if(elem == null){
            throwArgumentException(RSS_GUID);
        }
        return elem.getText();
    }

    public static String getRssDescription(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_DESCRIPTION);
        if(elem == null){
            throwArgumentException(RSS_DESCRIPTION);
        }
        return elem.getText();

    }

    public static String getRssCategory(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_CATEGORY);
        if(elem == null){
            throwArgumentException(RSS_CATEGORY);
        }
        return elem.getText();
    }

    public static XMLEnclosure getRssEnclosure(Element element) throws IllegalArgumentException{
        checkNullElement(element);

        Element elem = element.getChildByName(RSS_EPISODE);
        if(elem == null){
            throwArgumentException(RSS_EPISODE);
        }

        return new XMLEnclosure(Utils.atof(elem.getAttribute(XMLEnclosure.LENGTH_ATTRIBUTE)),
                elem.getAttribute(XMLEnclosure.TYPE_ATTRIBUTE),
                elem.getAttribute(XMLEnclosure.URL_ATTRIBUTE));
    }

    public static List<RodkastEpisode> getCompleteEpisodesList(Element element) {
        if(element != null){
            Array<Element> items = element.getChildrenByName(RodkastItemModel.RSS_ITEM);

            try {
                return buildRodkestEpisodes(items);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static List<RodkastEpisode> buildRodkestEpisodes(Array<Element> items) throws MalformedURLException {
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

   /* public static Map<String, XMLImage> getRssImages(Element item){
        checkNullElement(item);
        for()
    }*/

    public static String getRssLanguage(Element element) throws IllegalArgumentException{
        checkNullElement(element);

        Element elem = element.getChildByName(RSS_LANGUAGE);
        if(elem == null){
            throwArgumentException(RSS_LANGUAGE);
        }
        return elem.getText();
    }

    private static XMLImage getRssImage(Element element) throws IllegalArgumentException{
        checkNullElement(element);

        Element elem = element.getChildByName(RSS_IMAGE);
        if(elem == null){
            throwArgumentException(RSS_IMAGE);
        }

        return new XMLImage(elem.getAttribute(XMLImage.TITLE_ATTRIBUTE),
                elem.getAttribute(XMLImage.LINK_ATTRIBUTE),
                elem.getAttribute(XMLImage.URL_ATTRIBUTE));
    }

    private static void throwArgumentException(String name){
        throw new IllegalArgumentException(name + " attribute could not be found");
    }

    private static void checkNullElement(Element element){
        if(element == null){
            throw new IllegalArgumentException("Element item cannot be null");
        }
    }

    private static Calendar parseDate(String date) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            Date parsedDate = new SimpleDateFormat(RSS_DATE_PATTERN, Locale.US).parse(date);
            calendar.setTime(parsedDate);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }
}
