package net.asg.game.utils.rodkast;

import com.badlogic.gdx.utils.Array;

import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.parser.RodkastChannel;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.XMLHandler;
import net.asg.game.utils.parser.gdxtests.GdxTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Blakbro2k on 10/17/2017.
 */

@RunWith(GdxTestRunner.class)
public class AudioUtilsTest {
    private XMLHandler xmlHandler;
    private RodkastChannel rssChannel;
    private Array<RodkastEpisode> episodes;

    public void setUp() throws Exception {
        xmlHandler = new XMLHandler();

        rssChannel = xmlHandler.buildChannel();

        if(rssChannel == null) {
            throw new Exception("rssChannel is null");
        }

        episodes = rssChannel.getEpisodes();
    }

    public void tearDown() throws Exception {
        xmlHandler.dispose();
        rssChannel.dispose();
        episodes.clear();
        episodes = null;
    }

    @Test
    public void dispose() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void playEpisode() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void setEpisodePosition() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void getEpisodePositionValue() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void dowloadEpisode() throws Exception {
        AudioUtils.getInstance().dowloadEpisode(episodes.peek());
        throw new Exception("Test not initialized");
    }

    @Test
    public void getAudioDownloadProgressValue() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void addAudioToIndex() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void getAudioFromIndex() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void getFullFilePath() throws Exception {
        throw new Exception("Test not initialized");
    }

}