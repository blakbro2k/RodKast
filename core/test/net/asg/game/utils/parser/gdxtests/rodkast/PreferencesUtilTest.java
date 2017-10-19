package net.asg.game.utils.parser.gdxtests.rodkast;

import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.PreferencesUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eboateng on 10/19/2017.
 */
public class PreferencesUtilTest {
    boolean initialized = false;

    @Before
    public void setUP() throws Exception {
        if(!initialized){
            PreferencesUtil.getPreferences();
            PreferencesUtil.setStoragePref(true);
            PreferencesUtil.setStoragePathPref(null);
        }
    }


   @Test
    public void saveBoolean() throws Exception {

    }

    @Test
    public void saveString() throws Exception {

    }

    @Test
    public void saveInteger() throws Exception {

    }

    @Test
    public void saveLong() throws Exception {

    }

    @Test
    public void saveFloat() throws Exception {

    }

    @Test
    public void saveValues() throws Exception {

    }

    @Test
    public void getPreferences() throws Exception {

    }

    @Test
    public void getStoragePathPref() throws Exception {

    }

    @Test
    public void getStoragePref() throws Exception {
        PreferencesUtil.setStoragePref(true);
        boolean useExternal = PreferencesUtil.getStoragePref();
        Assert.assertTrue("Set to use External file should be true: " + useExternal, useExternal);
        PreferencesUtil.setStoragePref(false);
        useExternal = PreferencesUtil.getStoragePref();
        Assert.assertFalse("Set to use External file should be false: " + useExternal, useExternal);
    }

    @Test
    public void getInternetOnlyPref() throws Exception {
        PreferencesUtil.setInternetOnlyPref(true);
        boolean useExternal = PreferencesUtil.getInternetOnlyPref();
        Assert.assertTrue("Set to use External file should be true: " + useExternal, useExternal);
        PreferencesUtil.setInternetOnlyPref(false);
        useExternal = PreferencesUtil.getInternetOnlyPref();
        Assert.assertFalse("Set to use External file should be false: " + useExternal, useExternal);
    }

    @Test
    public void setStoragePref() throws Exception {
        PreferencesUtil.setStoragePref(false);
        Assert.assertFalse(PreferencesUtil.getStoragePref());
        PreferencesUtil.setStoragePref(true);
        Assert.assertTrue(PreferencesUtil.getStoragePref());
    }

    @Test
    public void setInternetOnlyPref() throws Exception {
        PreferencesUtil.setInternetOnlyPref(false);
        Assert.assertFalse(PreferencesUtil.getInternetOnlyPref());
        PreferencesUtil.setInternetOnlyPref(true);
        Assert.assertTrue(PreferencesUtil.getInternetOnlyPref());
    }

    @Test
    public void setStoragePathPref() throws Exception {
        PreferencesUtil.setStoragePathPref(null);
        Assert.assertEquals(GlobalConstants.DEFAULT_DOWNLOAD_FOLDER, PreferencesUtil.getStoragePathPref());

        PreferencesUtil.setStoragePathPref("");
        Assert.assertEquals("", PreferencesUtil.getStoragePathPref());

        PreferencesUtil.setStoragePathPref("rodkast/place");
        Assert.assertEquals("rodkast/place", PreferencesUtil.getStoragePathPref());

        String invalidPath = "[v:a<l\"id*Pa|th?Now>]";
        String validPath = "this/is/valid";
        String validPathNow = "[validPathNow]";

        PreferencesUtil.setStoragePathPref(invalidPath);
        Assert.assertEquals(validPathNow, PreferencesUtil.getStoragePathPref());
        PreferencesUtil.setStoragePathPref(validPath);
        Assert.assertEquals(validPath, PreferencesUtil.getStoragePathPref());
    }

    @Test
    public void toggleStoragePref() throws Exception {
        PreferencesUtil.setStoragePref(false);
        Assert.assertFalse(PreferencesUtil.getStoragePref());
        PreferencesUtil.toggleStoragePref();
        Assert.assertTrue(PreferencesUtil.getStoragePref());
        PreferencesUtil.toggleStoragePref();
        Assert.assertFalse(PreferencesUtil.getStoragePref());
    }
}