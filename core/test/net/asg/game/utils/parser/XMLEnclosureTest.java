package net.asg.game.utils.parser;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eboateng on 10/9/2017.
 */

public class XMLEnclosureTest {

    @Test
    public void getLength() throws Exception {
        XMLEnclosure zeforLength = new XMLEnclosure(0,"mpge","http://rodkast.com/feed/podcast");
        XMLEnclosure nonZeforLength = new XMLEnclosure(10,"mpge","http://rodkast.com/feed/podcast");


        try{
            XMLEnclosure negLength = new XMLEnclosure(-1,"mpge","http://rodkast.com/feed/podcast");
        } catch (Exception ille){
            Assert.assertTrue(ille instanceof IllegalArgumentException);
        }

        Assert.assertEquals(0f,zeforLength.getLength(),0);
        Assert.assertEquals(10f,nonZeforLength.getLength(),0);
    }

    @Test
    public void getType() throws Exception {
        XMLEnclosure goodType = new XMLEnclosure(10,"mpge","http://rodkast.com/feed/podcast");

        try{
            XMLEnclosure nullType = new XMLEnclosure(-1,null,"http://rodkast.com/feed/podcast");
        } catch (Exception ille){
            Assert.assertTrue(ille instanceof IllegalArgumentException);
        }

        Assert.assertEquals("mpge",goodType.getType());
    }

    @Test
    public void getUrl() throws Exception {
        XMLEnclosure goodType = new XMLEnclosure(10,"mpge","http://rodkast.com/feed/podcast");
        URL expectedURL = new URL("http://rodkast.com/feed/podcast");
        //XMLEnclosure nonZeforLength = new XMLEnclosure(10,"mpge","http://rodkast.com/feed/podcast");

        try{
            XMLEnclosure nullType = new XMLEnclosure(11,"mpge",null);
        } catch (Exception ille){
            Assert.assertTrue(ille instanceof MalformedURLException);
        }

        Assert.assertEquals(expectedURL,goodType.getUrl());
    }
}

