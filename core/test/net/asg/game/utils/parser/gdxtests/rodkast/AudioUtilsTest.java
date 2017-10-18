package net.asg.game.utils.parser.gdxtests.rodkast;

import com.badlogic.gdx.utils.Array;

import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.parser.RodkastChannel;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.XMLHandler;
import net.asg.game.utils.parser.gdxtests.GdxTestRunner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
        xmlHandler = new XMLHandler();

        rssChannel = xmlHandler.buildChannel();

        if(rssChannel == null) {
            throw new Exception("rssChannel is null");
        }

        episodes = rssChannel.getEpisodes();
    }

    @After
    public void tearDown() throws Exception {
        xmlHandler.dispose();
        rssChannel.dispose();
        episodes.clear();
        episodes = null;
    }

    @Test
    public void getStoragePathPref() throws Exception {
        AudioUtils.getInstance().setStoragePathPref("");
        Assert.assertEquals("", AudioUtils.getInstance().getStoragePathPref());

        AudioUtils.getInstance().setStoragePathPref("rodkast");
        Assert.assertEquals("rodkast", AudioUtils.getInstance().getStoragePathPref());

        //AudioUtils.getInstance().setStoragePref(false);
        //useExternal = AudioUtils.getInstance().getStoragePref();
        //Assert.assertFalse("Set to use External file should be false: " + useExternal, useExternal);
    }

    @Test
    public void getStoragePref() throws Exception {
        AudioUtils.getInstance().setStoragePref(true);
        boolean useExternal = AudioUtils.getInstance().getStoragePref();
        Assert.assertTrue("Set to use External file should be true: " + useExternal, useExternal);
        AudioUtils.getInstance().setStoragePref(false);
        useExternal = AudioUtils.getInstance().getStoragePref();
        Assert.assertFalse("Set to use External file should be false: " + useExternal, useExternal);
    }

    @Test
    public void getInternetOnlyPref() throws Exception {
        AudioUtils.getInstance().setInternetOnlyPref(true);
        boolean useExternal = AudioUtils.getInstance().getInternetOnlyPref();
        Assert.assertTrue("Set to use External file should be true: " + useExternal, useExternal);
        AudioUtils.getInstance().setInternetOnlyPref(false);
        useExternal = AudioUtils.getInstance().getInternetOnlyPref();
        Assert.assertFalse("Set to use External file should be false: " + useExternal, useExternal);
    }

    @Test
    public void setStoragePref() throws Exception {
        AudioUtils.getInstance().setStoragePref(false);
        Assert.assertFalse(AudioUtils.getInstance().getStoragePref());
        AudioUtils.getInstance().setStoragePref(true);
        Assert.assertTrue(AudioUtils.getInstance().getStoragePref());
    }

    @Test
    public void setInternetOnlyPref() throws Exception {
        AudioUtils.getInstance().setInternetOnlyPref(false);
        Assert.assertFalse(AudioUtils.getInstance().getInternetOnlyPref());
        AudioUtils.getInstance().setInternetOnlyPref(true);
        Assert.assertTrue(AudioUtils.getInstance().getInternetOnlyPref());
    }

    @Test
    public void setStoragePathPref() throws Exception {
        AudioUtils.getInstance().setStoragePathPref("");
        Assert.assertEquals("", AudioUtils.getInstance().getStoragePathPref());

        AudioUtils.getInstance().setStoragePathPref("rodkast/place");
        Assert.assertEquals("rodkast/place", AudioUtils.getInstance().getStoragePathPref());

        AudioUtils.getInstance().setStoragePathPref(null);
        Assert.assertEquals("rodkast", AudioUtils.getInstance().getStoragePathPref());

        String invalidPath = "[v:a<l\"id*Pa|th?Now>]";
        String validPath = "this/is/valid";
        String validPathNow = "[validPathNow]";

        AudioUtils.getInstance().setStoragePathPref(invalidPath);
        Assert.assertEquals(validPathNow, AudioUtils.getInstance().getStoragePathPref());
        AudioUtils.getInstance().setStoragePathPref(validPath);
        Assert.assertEquals(validPath, AudioUtils.getInstance().getStoragePathPref());
    }

    @Test
    public void toggleStoragePref() throws Exception {
        AudioUtils.getInstance().setStoragePref(false);
        Assert.assertFalse(AudioUtils.getInstance().getStoragePref());
        AudioUtils.getInstance().toggleStoragePref();
        Assert.assertTrue(AudioUtils.getInstance().getStoragePref());
        AudioUtils.getInstance().toggleStoragePref();
        Assert.assertFalse(AudioUtils.getInstance().getStoragePref());
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