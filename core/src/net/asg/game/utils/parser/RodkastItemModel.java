package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.XmlReader.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by eboateng on 7/18/2017.
 */

public class RodkastItemModel{
    public static final String RODKAST_URL_STRING = "http://rodkast.com/feed/podcast/";
    public static final String RSS_TITLE = "title";
    public static final String RSS_LINK = "link";
    public static final String RSS_PUBLISHED_DATE = "pubDate";
    public static final String RSS_ITEM = "item";
    public static final String RSS_DESCRIPTION = "description";
    public static final String RSS_CATEGORY = "category";
    public static final String RSS_GUID = "guid";
    public static final String RSS_COMMENTS = "comments";
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

        Element elem = item.getChildByName(RSS_PUBLISHED_DATE);
        if(elem == null){
            throwArgumentException(RSS_PUBLISHED_DATE);
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

    public static XMLEnclosure getRssEnclosure(Element item) throws IllegalArgumentException{
        checkNullElement(item);

        Element elem = item.getChildByName(RSS_EPISODE);
        if(elem == null){
            throwArgumentException(RSS_EPISODE);
        }

        return new XMLEnclosure(Float.parseFloat(elem.getAttribute("length")),
                elem.getAttribute("type"),
                elem.getAttribute("url"));
    }

    private static void throwArgumentException(String name){
        throw new IllegalArgumentException(name + " attribute could not be found");
    }

    private static void checkNullElement(Element item){
        if(item == null){
            throw new IllegalArgumentException("Element item cannot be null");
        }
    }

    public static Calendar parseDate(String date) {
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
