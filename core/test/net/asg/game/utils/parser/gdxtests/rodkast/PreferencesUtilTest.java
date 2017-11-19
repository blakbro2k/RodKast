package net.asg.game.utils.parser.gdxtests.rodkast;

import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.PreferencesUtil;
import net.asg.game.utils.parser.gdxtests.GdxTestRunner;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by eboateng on 10/19/2017.
 */
@RunWith(GdxTestRunner.class)
public class PreferencesUtilTest {


    @Test
    public void saveBoolean() throws Exception {
        PreferencesUtil.saveBoolean("testBool", false);
        Assert.assertFalse(PreferencesUtil.getPreferences().getBoolean("testBool"));
        PreferencesUtil.saveBoolean("testBool", true);
        Assert.assertTrue(PreferencesUtil.getPreferences().getBoolean("testBool"));
    }

    @Test
    public void saveString() throws Exception {
        try{
            PreferencesUtil.saveString("testString", null);
        } catch (Exception e) {
            System.out.println("saveString(): Caught: " + e);
            Assert.assertTrue(e instanceof GdxRuntimeException);
        }

        PreferencesUtil.saveString("testString", "testString");
        Assert.assertNotNull(PreferencesUtil.getPreferences().getBoolean("testString"));
    }

    @Test
    public void saveInteger() throws Exception {
        PreferencesUtil.saveInteger("testInteger", 256);
        Assert.assertEquals(256, PreferencesUtil.getPreferences().getInteger("testInteger"),0);
        PreferencesUtil.saveInteger("testInteger", -256);
        Assert.assertEquals(-256, PreferencesUtil.getPreferences().getInteger("testInteger"),0);
    }

    @Test
    public void saveLong() throws Exception {
        PreferencesUtil.saveLong("testLong", 5416654644L);
        Assert.assertEquals(5416654644L, PreferencesUtil.getPreferences().getLong("testLong"),0);
        PreferencesUtil.saveLong("testLong", -5416654644L);
        Assert.assertEquals(-5416654644L, PreferencesUtil.getPreferences().getLong("testLong"),0);
    }

    @Test
    public void saveFloat() throws Exception {
        PreferencesUtil.saveFloat("testFloat", 1f);
        Assert.assertEquals(1f, PreferencesUtil.getPreferences().getFloat("testFloat"), 0);
        PreferencesUtil.saveFloat("testFloat", 0f);
        Assert.assertEquals(0f, PreferencesUtil.getPreferences().getFloat("testFloat"), 0);
        PreferencesUtil.saveFloat("testFloat", 0.5f);
        Assert.assertEquals(0.5f, PreferencesUtil.getPreferences().getFloat("testFloat"), 0);
    }

    @Test
    public void saveValues() throws Exception {
        Map testMixValues = new Hashtable();
        testMixValues.put("testValsBool", true);
        testMixValues.put("testValsString", "String");
        testMixValues.put("testValsFloat", 1f);
        testMixValues.put("testValsLong", 42324L);
       // testMixValues.put("bool", true);

        PreferencesUtil.saveValues(testMixValues);

        Map values = PreferencesUtil.getPreferences().get();
        Assert.assertEquals("true", values.get("testValsBool"));
        Assert.assertEquals("String", values.get("testValsString"));
        Assert.assertEquals("1.0", values.get("testValsFloat"));
        Assert.assertEquals("42324", values.get("testValsLong"));

       // throw new Exception("Test not initialized.");
    }

    @Test
    public void getPreferences() throws Exception {
        Assert.assertNotNull(PreferencesUtil.getPreferences());
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