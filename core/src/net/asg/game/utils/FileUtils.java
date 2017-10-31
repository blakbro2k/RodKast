package net.asg.game.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by eboateng on 10/19/2017.
 */

public class FileUtils {
    public static boolean isFileDownloaded(String fileName) {
        return fileName != null && PreferencesUtil.getStoragePref()? Gdx.files.external(fileName).exists() : Gdx.files.internal(fileName).exists();
    }
}
