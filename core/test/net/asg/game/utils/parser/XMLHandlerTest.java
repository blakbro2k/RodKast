package net.asg.game.utils.parser;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Blakbro2k on 7/23/2017.
 */
public class XMLHandlerTest {
    XMLHandler xmlHandler;

    @Before
    public void setUp() throws Exception {
        xmlHandler = new XMLHandler();

        try {
            xmlHandler.getTotalRssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseFeed() throws Exception {
        URL expectedURL = new URL("http://rodkast.com/feed/podcast/");
        assertEquals(expectedURL,xmlHandler.urlLink);
        assertTrue(xmlHandler.isFeedFetched);
    }

    @Test
    public void buildChannel() throws Exception {

    }

    @Test
    public void getEpisodes() throws Exception {
        List<RodkastEpisode> episodes = xmlHandler.;
        RodkastEpisode episode = episodes.get(0);
        System.out.println(episode);
        System.out.println(episode.getCategory());
        System.out.println(episode.getDescription());
        //System.out.println(episode.getEpisode());
        System.out.println(episode.getMediaLink());
        System.out.println(episode.getGuid());
        System.out.println(episode.getPubishedDate());
        System.out.println(episode.getTitle());
        System.out.println(episode.getWebLink());
    }
}