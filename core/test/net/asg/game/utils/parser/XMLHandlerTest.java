package net.asg.game.utils.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by Blakbro2k on 7/23/2017.
 */
public class XMLHandlerTest {
    XMLHandler xmlHandler;

    @Before
    public void setUp() throws Exception {
        xmlHandler = new XMLHandler();

        try {
            xmlHandler.getRssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getXMLstream() throws IOException {
        InputStream stream = xmlHandler.getXMLstream();
        Assert.assertNotNull(stream);
        Assert.assertTrue(stream instanceof InputStream);
    }

    @Test
    public void isFetched(){

    }

    @Test
    public void buildChannel() throws IOException, ParseException {

    }
}
