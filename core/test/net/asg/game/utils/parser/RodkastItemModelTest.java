package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.XmlReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by eboateng on 10/13/2017.
 */
public class RodkastItemModelTest {
    XmlReader.Element parent;
    private final String GOOD_LINK = "https://example.com";
    private final String BAD_LINK = "https:/example.com";
    private final String EMPTY_STRING = "";

    @Before
    public void setUp() throws Exception {
        parent = new XmlReader.Element("channel",null);
        XmlReader.Element link = new XmlReader.Element("link",parent);

        link.setText(GOOD_LINK);
    }

    @After
    public void tearDown() throws Exception {
        parent = null;
    }

    @Test
    public void getRssUrl() throws Exception {
        //is missing
        try{
            RodkastItemModel.getRssUrl(parent);
        } catch(Exception e){
            System.out.println("getRssUrl() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //is null
        XmlReader.Element element = new XmlReader.Element(RodkastItemModel.RSS_LINK,parent);

        parent.addChild(element);
        try{
            RodkastItemModel.getRssUrl(parent);
        } catch (Exception e){
            System.out.println("getRssUrl() error message: " + e);
            Assert.assertTrue(e instanceof MalformedURLException);
        }
        parent.removeChild(element);

        //is set good link
        element.setText(GOOD_LINK);
        parent.addChild(element);
        URL expected = new URL(GOOD_LINK);
        Assert.assertEquals(expected,RodkastItemModel.getRssUrl(parent));

        //is empty
        element.setText(EMPTY_STRING);
        parent.addChild(element);
        try{
            RodkastItemModel.getRssUrl(parent);
        } catch (Exception e){
            System.out.println("getRssUrl() error message: " + e);
            Assert.assertTrue(e instanceof MalformedURLException);
        }
        parent.removeChild(element);

        //is bad link
        element.setText(BAD_LINK);
        parent.addChild(element);
        try{
            RodkastItemModel.getRssUrl(parent);
        } catch (Exception e){
            System.out.println("getRssUrl() error message: " + e);
            Assert.assertTrue(e instanceof MalformedURLException);
        }
        parent.removeChild(element);

        //is bad link
        element.setText("https://example.com?/fkljsdf/kjfdls[");
        parent.addChild(element);
        try{
            RodkastItemModel.getRssUrl(parent);
        } catch (Exception e){
            System.out.println("getRssUrl() error message: " + e);
            Assert.assertTrue(e instanceof MalformedURLException);
        }
        parent.removeChild(element);
    }

    @Test
    public void getRssPubDate() throws Exception {
        String DATE = "Thu, 05 Oct 2017 02:58:46 +0000";

        //is missing
        try{
            RodkastItemModel.getRssPubDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        XmlReader.Element element = new XmlReader.Element(RodkastItemModel.RSS_PUBLISHED_DATE,parent);
        //is null
        parent.addChild(element);
        try{
            RodkastItemModel.getRssPubDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(element);

        //is unparsable
        element.setText(RodkastItemModel.RSS_PUBLISHED_DATE);
        parent.addChild(element);
        try{
            RodkastItemModel.getRssPubDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof ParseException);
        }
        parent.removeChild(element);

        //is empty
        element.setText(EMPTY_STRING);
        parent.addChild(element);
        try{
            RodkastItemModel.getRssPubDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof ParseException);
        }
        parent.removeChild(element);

        //is set
        element.setText(DATE);
        GregorianCalendar expected = new GregorianCalendar();

        Date expectedDate = new SimpleDateFormat(RodkastItemModel.RSS_DATE_PATTERN, Locale.US).parse(DATE);
        expected.setTime(expectedDate);

        parent.addChild(element);
        Assert.assertEquals(expected,RodkastItemModel.getRssPubDate(parent));
        parent.removeChild(element);
    }

    @Test
    public void getLastBuildDate() throws Exception {
        String DATE = "Thu, 05 Oct 2017 02:58:46 +0000";

        //is missing
        try{
            RodkastItemModel.getLastBuildDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        XmlReader.Element element = new XmlReader.Element(RodkastItemModel.RSS_LAST_BUILD_DATE,parent);
        //is null
        parent.addChild(element);
        try{
            RodkastItemModel.getLastBuildDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(element);

        //is unparsable
        element.setText(RodkastItemModel.RSS_LAST_BUILD_DATE);
        parent.addChild(element);
        try{
            RodkastItemModel.getLastBuildDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof ParseException);
        }
        parent.removeChild(element);

        //is empty
        element.setText(EMPTY_STRING);
        parent.addChild(element);
        try{
            RodkastItemModel.getLastBuildDate(parent);
        } catch(Exception e){
            System.out.println("getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof ParseException);
        }
        parent.removeChild(element);

        //is set
        element.setText(DATE);
        GregorianCalendar expected = new GregorianCalendar();

        Date expectedDate = new SimpleDateFormat(RodkastItemModel.RSS_DATE_PATTERN, Locale.US).parse(DATE);
        expected.setTime(expectedDate);

        parent.addChild(element);
        Assert.assertEquals(expected,RodkastItemModel.getLastBuildDate(parent));
        parent.removeChild(element);
    }

    @Test
    public void getRssTitle() throws Exception {
        //is missing
        try{
            RodkastItemModel.getRssTitle(parent);
        } catch(Exception e){
            System.out.println("getRssTitle() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //is null
        XmlReader.Element element = new XmlReader.Element(RodkastItemModel.RSS_TITLE,parent);
        parent.addChild(element);
        Assert.assertNull(RodkastItemModel.RSS_TITLE,RodkastItemModel.getRssTitle(parent));
        parent.removeChild(element);

        //is set
        element.setText(RodkastItemModel.RSS_TITLE);
        parent.addChild(element);
        Assert.assertEquals(RodkastItemModel.RSS_TITLE,RodkastItemModel.getRssTitle(parent));

        //is empty
        element.setText(EMPTY_STRING);
        parent.addChild(element);
        Assert.assertEquals(EMPTY_STRING,RodkastItemModel.getRssTitle(parent));
        parent.removeChild(element);
    }

    @Test
    public void getRssGUID() throws Exception {
        //is missing
        try{
            RodkastItemModel.getRssGUID(parent);
        } catch(Exception e){
            System.out.println("getRssGUID() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //RSS_GUID is null
        XmlReader.Element language = new XmlReader.Element(RodkastItemModel.RSS_GUID,parent);
        parent.addChild(language);
        Assert.assertNull(RodkastItemModel.RSS_GUID,RodkastItemModel.getRssGUID(parent));
        parent.removeChild(language);

        //RSS_GUID is set
        language.setText(RodkastItemModel.RSS_GUID);
        parent.addChild(language);
        Assert.assertEquals(RodkastItemModel.RSS_GUID,RodkastItemModel.getRssGUID(parent));

        //RSS_GUID is empty
        language.setText(EMPTY_STRING);
        parent.addChild(language);
        Assert.assertEquals(EMPTY_STRING,RodkastItemModel.getRssGUID(parent));
        parent.removeChild(language);
    }

    @Test
    public void getRssDescription() throws Exception {
        //Description is missing
        try{
            RodkastItemModel.getRssDescription(parent);
        } catch(Exception e){
            System.out.println("getRssDescription() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //Description is null
        XmlReader.Element description = new XmlReader.Element(RodkastItemModel.RSS_DESCRIPTION,parent);
        parent.addChild(description);
        Assert.assertNull(RodkastItemModel.RSS_DESCRIPTION,RodkastItemModel.getRssDescription(parent));
        parent.removeChild(description);

        //Description is set
        description.setText(RodkastItemModel.RSS_DESCRIPTION);
        parent.addChild(description);
        Assert.assertEquals(RodkastItemModel.RSS_DESCRIPTION,RodkastItemModel.getRssDescription(parent));

        //Description is empty
        description.setText(EMPTY_STRING);
        parent.addChild(description);
        Assert.assertEquals(EMPTY_STRING,RodkastItemModel.getRssDescription(parent));
        parent.removeChild(description);
    }

    @Test
    public void getRssCategory() throws Exception {
        //Category is missing
        try{
            RodkastItemModel.getRssCategory(parent);
        } catch(Exception e){
            System.out.println("getRssCategory() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //Category is null
        XmlReader.Element category = new XmlReader.Element(RodkastItemModel.RSS_CATEGORY,parent);
        parent.addChild(category);
        Assert.assertNull(RodkastItemModel.RSS_CATEGORY,RodkastItemModel.getRssCategory(parent));
        parent.removeChild(category);

        //Category is set
        category.setText(RodkastItemModel.RSS_CATEGORY);
        parent.addChild(category);
        Assert.assertEquals(RodkastItemModel.RSS_CATEGORY,RodkastItemModel.getRssCategory(parent));

        //Category is empty
        category.setText(EMPTY_STRING);
        parent.addChild(category);
        Assert.assertEquals(EMPTY_STRING,RodkastItemModel.getRssCategory(parent));
        parent.removeChild(category);
    }

    @Test
    public void getRssLanguage() throws Exception {
        //Language is missing
        try{
            RodkastItemModel.getRssLanguage(parent);
        } catch(Exception e){
            System.out.println("getRssLanguage() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //Language is null
        XmlReader.Element language = new XmlReader.Element(RodkastItemModel.RSS_LANGUAGE,parent);
        parent.addChild(language);
        Assert.assertNull(RodkastItemModel.RSS_LANGUAGE,RodkastItemModel.getRssLanguage(parent));
        parent.removeChild(language);

        //Language is set
        language.setText(RodkastItemModel.RSS_LANGUAGE);
        parent.addChild(language);
        Assert.assertEquals(RodkastItemModel.RSS_LANGUAGE,RodkastItemModel.getRssLanguage(parent));

        //Language is empty
        language.setText(EMPTY_STRING);
        parent.addChild(language);
        Assert.assertEquals(EMPTY_STRING,RodkastItemModel.getRssLanguage(parent));
        parent.removeChild(language);
    }

    @Test
    public void getRssEnclosure() throws Exception {
        //is missing
        try{
            RodkastItemModel.getRssEnclosure(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //is null
        XmlReader.Element testEnclosure = new XmlReader.Element(RodkastItemModel.RSS_EPISODE,parent);
        testEnclosure.setAttribute(XMLEnclosure.URL_ATTRIBUTE, null);
        testEnclosure.setAttribute(XMLEnclosure.LENGTH_ATTRIBUTE, null);
        testEnclosure.setAttribute(XMLEnclosure.TYPE_ATTRIBUTE, null);

        String url = "http://media.blubrry.com/rodkast/traffic.libsyn.com/rodkast/Rodkast111.mp3";
        String type = "audio/mpeg";
        float length = 59764337f;

        XMLEnclosure expected = new XMLEnclosure(length, type, url);

        parent.addChild(testEnclosure);
        try{
            RodkastItemModel.getRssEnclosure(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(testEnclosure);

        testEnclosure.setAttribute(XMLEnclosure.LENGTH_ATTRIBUTE, length + "");
        parent.addChild(testEnclosure);
        try{
            RodkastItemModel.getRssEnclosure(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(testEnclosure);

        testEnclosure.setAttribute(XMLEnclosure.TYPE_ATTRIBUTE, type);
        parent.addChild(testEnclosure);
        try{
            RodkastItemModel.getRssEnclosure(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(testEnclosure);

        testEnclosure.setAttribute(XMLEnclosure.URL_ATTRIBUTE, url);
        parent.addChild(testEnclosure);
        try{
            RodkastItemModel.getRssEnclosure(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(testEnclosure);

        //matches
        parent.addChild(testEnclosure);
        Assert.assertEquals(expected.getLength(),RodkastItemModel.getRssEnclosure(parent).getLength(),0);
        Assert.assertEquals(expected.getType(),RodkastItemModel.getRssEnclosure(parent).getType());
        Assert.assertEquals(expected.getUrl(),RodkastItemModel.getRssEnclosure(parent).getUrl());
        parent.removeChild(testEnclosure);
    }

    @Test
    public void getCompleteEpisodesList() throws Exception {
        //is missing
        try{
            RodkastItemModel.getCompleteEpisodesList(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        throw new Exception("Test not initialized.");
    }

    @Test
    public void getRssImages() throws Exception {
        //is missing
        try{
            RodkastItemModel.getRssImage(parent);
        } catch(Exception e){
            System.out.println("getRssEnclosure() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        XmlReader.Element element = new XmlReader.Element(RodkastItemModel.RSS_IMAGE,parent);
        //is null
        parent.addChild(element);
        try{
            RodkastItemModel.getRssImage(parent);
        } catch(Exception e){
            System.out.println(element + "getLastBuildDate() error message: " + e);
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        parent.removeChild(element);

        throw new Exception("Test not initialized.");
    }
}