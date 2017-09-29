package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.XmlReader;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.net.URL;

import static org.testng.Assert.*;

/**
 * Created by Blakbro2k on 9/29/2017.
 */
public class RodkastEpisodeTest {
    InputStream inputStream;
    RodkastEpisode episode;

    @BeforeMethod
    public void setUp() throws Exception {
        URL urlLink = new URL(RodkastItemModel.RODKAST_URL_STRING);
        InputStream inputStream = urlLink.openConnection().getInputStream();
        XmlReader reader = new XmlReader();

        XmlReader.Element xmlElements = reader.parse(inputStream);
        XmlReader.Element elem = xmlElements.getChildByName(RodkastItemModel.RSS_CHANNEL);

        episode = new RodkastEpisode(elem);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if(inputStream != null){
            inputStream.close();
        }
    }

    @Test
    public void testGetTitle() throws Exception {
        episode.getTitle();
    }

    @Test
    public void testGetWebLink() throws Exception {

    }

    @Test
    public void testGetPubishedDate() throws Exception {

    }

    @Test
    public void testGetGuid() throws Exception {

    }

    @Test
    public void testGetDescription() throws Exception {

    }

    @Test
    public void testGetCategory() throws Exception {

    }

    @Test
    public void testGetMediaLink() throws Exception {

    }

    @Test
    public void testGetEnclosure() throws Exception {

    }

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testGetDuration() throws Exception {

    }

}