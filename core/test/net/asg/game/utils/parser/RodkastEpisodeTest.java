package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.XmlReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Blakbro2k on 9/29/2017.
 */
public class RodkastEpisodeTest {
    InputStream inputStream;
    RodkastEpisode episode;

    @Before
    public void setUp() throws Exception {
        URL urlLink = new URL(RodkastItemModel.RODKAST_URL_STRING);
        InputStream inputStream = urlLink.openConnection().getInputStream();
        XmlReader reader = new XmlReader();

        XmlReader.Element xmlElements = reader.parse(inputStream);
        XmlReader.Element elem = xmlElements.getChildByName(RodkastItemModel.RSS_CHANNEL);

        episode = new RodkastEpisode(elem);

        XmlReader.Element element = new XmlReader.Element(null, null);

    }

    @After
    public void tearDown() throws Exception {
        if(inputStream != null){
            inputStream.close();
        }
    }

    @Test
    public void testGetTitle() throws Exception {
        episode.getTitle();
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetWebLink() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetPubishedDate() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetGuid() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetDescription() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetCategory() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetMediaLink() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetEnclosure() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetType() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void testGetDuration() throws Exception {
        throw new Exception("Test not initialized");
    }
}