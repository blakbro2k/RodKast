package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Map;

/**
 * Created by eboateng on 10/19/2017.
 */

public class PreferencesUtil {
    private static final String EXTERNAL_STORAGE_PREFERENCE = "ext_storage"; //true = external; false = internal
    private static final String STORAGE_PATH_PREFERENCE = "storage_path";
    private static final String INTERNET_DOWNLOAD_PREFERENCE = "inet_only";
    private static final String INTERNAL_ASSETS_PATH = "data/";
    private static final String RESERVED_CHARACTERS_PATTERN = "[:*?\"<>|]";

    //Prevent instantiation
    private PreferencesUtil(){}

    public static Preferences getPreferences() {
        return Gdx.app.getPreferences(GlobalConstants.PREFERENCES_NAME);
    }

    public static String getStoragePathPref(){
        return getPreferences().getString(STORAGE_PATH_PREFERENCE, GlobalConstants.DEFAULT_DOWNLOAD_FOLDER);
    }

    public static boolean getStoragePref(){
        return getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, true);
    }

    public static boolean getInternetOnlyPref(){
        return getPreferences().getBoolean(INTERNET_DOWNLOAD_PREFERENCE, true);
    }

    public static void setStoragePref(boolean bool) {
        saveBoolean(EXTERNAL_STORAGE_PREFERENCE, bool);
    }

    public static void setInternetOnlyPref(boolean bool) {
        saveBoolean(INTERNET_DOWNLOAD_PREFERENCE, bool);
    }

    public static void setStoragePathPref(String path){
        if(path == null){
            path = GlobalConstants.DEFAULT_DOWNLOAD_FOLDER;
        }
        saveString(STORAGE_PATH_PREFERENCE, path.replaceAll(RESERVED_CHARACTERS_PATTERN, ""));
    }

    public static void toggleStoragePref() {
        saveBoolean(EXTERNAL_STORAGE_PREFERENCE, !getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, true));
    }

    public static void saveBoolean(String key, boolean value) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(key, value);
        preferences.flush();
    }

    public static void saveString(String key, String value){
        if(value == null){
            throw new GdxRuntimeException("Preference string cannot be null");
        }

        Preferences preferences = getPreferences();
        preferences.putString(key, value);
        preferences.flush();
    }

    public static void saveInteger(String key, int value) {
        Preferences preferences = getPreferences();
        preferences.putInteger(key, value);
        preferences.flush();
    }

    public static void saveLong(String key, long value) {
        Preferences preferences = getPreferences();
        preferences.putLong(key, value);
        preferences.flush();
    }

    public static void saveFloat(String key, float value) {
        Preferences preferences = getPreferences();
        preferences.putFloat(key, value);
        preferences.flush();
    }

    public static void saveValues(String key, Map<String, ?> values) {
        Preferences preferences = getPreferences();
        preferences.put(values);
        preferences.flush();
    }
}
