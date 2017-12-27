package net.asg.game.utils.rodkast;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.PreferencesUtil;
import net.asg.game.utils.parser.RodkastChannel;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.RodkastItemModel;
import net.asg.game.utils.parser.XMLHandler;
import net.asg.game.utils.parser.gdxtests.GdxTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

/**
 * Created by Blakbro2k on 10/17/2017.
 */

@RunWith(GdxTestRunner.class)
public class AudioUtilsTest {
    private XMLHandler xmlHandler;
    private RodkastChannel rssChannel;
    private Array<RodkastEpisode> episodes;

    public void setUp() throws Exception {
        if(xmlHandler == null){
            xmlHandler = new XMLHandler();

            rssChannel = xmlHandler.buildChannel();

            if(rssChannel == null) {
                throw new Exception("rssChannel is null");
            }

            episodes = rssChannel.getEpisodes();
        }
    }

    private XmlReader.Element getDummyEpisode(){
        XmlReader.Element item1 = new XmlReader.Element(RodkastItemModel.RSS_ITEM,null);

        String link = "http://media.blubrry.com/rodkast/traffic.libsyn.com/rodkast/Rodkast111.mp3";
        String title = "Rodkast Episode 8";
        String length = "";
        String description = "description";
        String category = "category";
        String guid = "597643375665446";
        String enclosure = "enclosure";
        String channel = "channel";
        String episode = "channel";

        item1.setAttribute(RodkastItemModel.RSS_TITLE, title);
        item1.setAttribute(RodkastItemModel.RSS_LINK, link);
        item1.setAttribute(RodkastItemModel.RSS_PUBLISHED_DATE, null);
        item1.setAttribute(RodkastItemModel.RSS_LAST_BUILD_DATE, null);
        item1.setAttribute(RodkastItemModel.RSS_ITEM, enclosure);
        item1.setAttribute(RodkastItemModel.RSS_DESCRIPTION, description);
        item1.setAttribute(RodkastItemModel.RSS_CATEGORY, category);
        item1.setAttribute(RodkastItemModel.RSS_GUID, guid);
        item1.setAttribute(RodkastItemModel.RSS_LANGUAGE, null);
        item1.setAttribute(RodkastItemModel.RSS_IMAGE, null);
        item1.setAttribute(RodkastItemModel.RSS_EPISODE, episode);
        item1.setAttribute(RodkastItemModel.RSS_CHANNEL, channel);

        return item1;
    }

    public void tearDown() throws Exception {
        xmlHandler.dispose();
        rssChannel.dispose();
        episodes.clear();
        episodes = null;
    }

    @Test
    public void dispose() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void addAudio() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void getAudio() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void isEpisodeDownloaded() throws Exception {
        setUp();
        RodkastEpisode episode = episodes.get(0);
        if(!AudioUtils.getInstance().isEpisodeDownloaded(episode)){
            AudioUtils.getInstance().dowloadEpisode(episode);
        }

        Assert.assertTrue(AudioUtils.getInstance().isEpisodeDownloaded(episode));

        RodkastEpisode falseEpisode = new RodkastEpisode(getDummyEpisode());
        Assert.assertFalse(AudioUtils.getInstance().isEpisodeDownloaded(falseEpisode));
    }

    @Test
    public void isFileDownloaded() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void playEpisode() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void setEpisodePosition() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void getEpisodePositionValue() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void dowloadEpisode() throws Exception {
        setUp();
        AudioUtils.getInstance().dowloadEpisode(episodes.peek());
        throw new Exception("Test not initialized");
    }

    @Test
    public void getAudioDownloadProgressValue() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void addAudioToIndex() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void getAudioFromIndex() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void getCurrentlyPlaying() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void pauseCurrentEpisode() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }

    @Test
    public void isCurrentlyPlaying() throws Exception {
        setUp();
        String testFileName = "testFileName";

        Assert.assertFalse(AudioUtils.getInstance().isCurrentlyPlaying(testFileName));
        //play audio
        Assert.assertTrue(AudioUtils.getInstance().isCurrentlyPlaying(testFileName));
    }

    @Test
    public void getFullFilePath() throws Exception {
        setUp();
        String testFileName = "testFileName";

        String expectedFilePath = PreferencesUtil.getStoragePathPref() + "\\" + testFileName;
        Assert.assertEquals(expectedFilePath, AudioUtils.getInstance().getFullFilePath(testFileName));
    }
    @Test
    public void getFileFromURL() throws Exception {
        setUp();
        XmlReader.Element episode = getDummyEpisode();
        String expectedFile = "Rodkast111.mp3";
        //System.out.println("get: " + episodes.get(0));
        URL testUrl = new URL(episode.getAttribute(RodkastItemModel.RSS_LINK));

        Assert.assertEquals(expectedFile, AudioUtils.getInstance().getFileFromURL(testUrl));
    }

    @Test
    public void createNewAudio() throws Exception {
        setUp();
        throw new Exception("Test not initialized");
    }
}