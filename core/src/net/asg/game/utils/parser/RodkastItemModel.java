package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

/**
 * Created by eboateng on 7/18/2017.
 */

class RodkastItemModel{
    static final String RODKAST_URL_STRING = "http://rodkast.com/feed/podcast/";
    private static final String ATTRIBUTE_STRING = "Attribute";
    private static final String ELEMENT_STRING = "Element";
    private static final String DATE_STRING = "Date";

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

    public static URL getRssUrl(Element element) throws MalformedURLException, IllegalArgumentException {
        return getXmlURLAttribute(RSS_LINK, element);
    }

    public static Calendar getRssPubDate(Element element) throws IllegalArgumentException, ParseException {
        return getXmlDateAttribute(RSS_PUBLISHED_DATE, element);
    }

    public static Calendar getLastBuildDate(Element element) throws IllegalArgumentException, ParseException {
        return getXmlDateAttribute(RSS_LAST_BUILD_DATE, element);
    }

    public static String getRssTitle(Element element) throws IllegalArgumentException{
        return getXmlStringAttribute(RSS_TITLE, element);
    }

    public static String getRssGUID(Element element) throws IllegalArgumentException{
        return getXmlStringAttribute(RSS_GUID, element);
    }

    public static String getRssDescription(Element element) throws IllegalArgumentException{
        return getXmlStringAttribute(RSS_DESCRIPTION, element);
    }

    public static String getRssCategory(Element element) throws IllegalArgumentException{
        return getXmlStringAttribute(RSS_CATEGORY, element);
    }

    public static String getRssLanguage(Element element) throws IllegalArgumentException{
        return getXmlStringAttribute(RSS_LANGUAGE, element);
    }

    public static XMLEnclosure getRssEnclosure(Element element) throws IllegalArgumentException, MalformedURLException {
        validateInput(element, ELEMENT_STRING);

        Element elem = element.getChildByName(RSS_EPISODE);
        if(elem == null){
            throwArgumentException(RSS_EPISODE);
        }

        return new XMLEnclosure(Utils.atof(elem.getAttribute(XMLEnclosure.LENGTH_ATTRIBUTE)),
                elem.getAttribute(XMLEnclosure.TYPE_ATTRIBUTE),
                elem.getAttribute(XMLEnclosure.URL_ATTRIBUTE));
    }

    public static Array<RodkastEpisode> getCompleteEpisodesList(Element element) throws MalformedURLException, ParseException {
        if(element != null){
            Array<Element> items = element.getChildrenByName(RodkastItemModel.RSS_ITEM);

            return buildRodkestEpisodes(items);
        }
        return null;
    }

    private static Array<RodkastEpisode> buildRodkestEpisodes(Array<Element> items) throws MalformedURLException, ParseException {
        Array<RodkastEpisode> episodes = null;

        if(items != null){
            episodes = new Array<>();
            for(Element item : items){
                if(item != null){
                    episodes.add(new RodkastEpisode(item));
                }
            }
        }
        return episodes;
    }

    public static Map<String,XMLImage> getRssImages(Element element) {
        return null;
    }

    private static XMLImage getRssImage(Element element) throws IllegalArgumentException, MalformedURLException {
        validateInput(element, ELEMENT_STRING);

        Element elem = element.getChildByName(RSS_IMAGE);
        if(elem == null){
            throwArgumentException(RSS_IMAGE);
        }

        return new XMLImage(elem.getAttribute(XMLImage.TITLE_ATTRIBUTE),
                elem.getAttribute(XMLImage.LINK_ATTRIBUTE),
                elem.getAttribute(XMLImage.URL_ATTRIBUTE));
    }

    private static void throwArgumentException(String name) throws IllegalArgumentException{
        throw new IllegalArgumentException(name + MessageCatalog.NULL_ATTRIBUTE_MSG);
    }

    private static void validateInput(Object object, String key) throws IllegalArgumentException{
        if(object == null){
            throwArgumentException(key);
        }
    }

    private static Calendar getXmlDateAttribute(String attr, Element element) throws IllegalArgumentException, ParseException {
        return parseDate(getValidatedChildByName(attr,element).getText());
    }

    private static String getXmlStringAttribute(String attr, Element element) throws IllegalArgumentException{
        return getValidatedChildByName(attr,element).getText();
    }

    private static URL getXmlURLAttribute(String attr, Element element) throws MalformedURLException {
        return new URL(getValidatedChildByName(attr,element).getText());
    }

    private static Element getValidatedChildByName(String attr, Element element) throws IllegalArgumentException{
        validateInput(element, ELEMENT_STRING);
        validateInput(attr, ATTRIBUTE_STRING);

        Element elem = element.getChildByName(attr);
        if(elem == null){
            throwArgumentException(attr);
        }
        return elem;
    }

    private static Calendar parseDate(String date) throws ParseException, IllegalArgumentException {
        GregorianCalendar calendar = new GregorianCalendar();
        validateInput(calendar, DATE_STRING);

        Date parsedDate = new SimpleDateFormat(RSS_DATE_PATTERN, Locale.US).parse(date);
        calendar.setTime(parsedDate);
        return calendar;
    }
}