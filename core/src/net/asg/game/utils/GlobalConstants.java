package net.asg.game.utils;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class GlobalConstants {
    public static final String USER_AGENT = "LMozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0";

    //prevent instantation
    private GlobalConstants(){}

    public static final int MAX_WIDTH = 1024;
    public static final int MAX_HEIGHT = 1024;
    public static final int APP_WIDTH = 480;
    public static final int APP_HEIGHT = 800;
    public static final int HTTP_REQUEST_TIMEOUT = 2500;
    public static final int ERROR_MSG_TIMEOUT = 8000;

    public static final int VIEWPORT_WIDTH = APP_WIDTH;
    public static final int VIEWPORT_HEIGHT = APP_HEIGHT;

    public static final String BAD_LOGIC_IMAGE_PATH = "data/badlogic.jpg";
    public static final String DEFAULT_FONT_PATH = "data/default.fnt";
    public static final String DEFAULT_FONT_IMAGE_PATH = "data/default.png";
    public static final String DEFAULT_UISKIN_ATLAS = "data/uiskin.atlas";
    public static final String DEFAULT_UISKIN_JSON = "data/uiskin.json";
    public static final String DEFAULT_ATLAS_PATH = "data/imageAssets.atlas";
    public static final String DEFAULT_DOWNLOAD_FOLDER = "rodkast";
    public static final String SHADE_UISKIN_ATLAS = "data/shade/uiskin.atlas";
    public static final String SHADE_UISKIN_JSON = "data/shade/uiskin.json";
    public static final String SHADE_ATLAS_PATH = "data/shade/imageAssets.atlas";

    public static final String GAME_TITLE = "RodKast Alpha";
    public static final String SOURCE_ASSETS_FOLDER_PATH = "desktop/";
    public static final String TARGET_ASSETS_FOLDER_PATH = "android/assets/data";
    public static final String IMAGES_FOLDER_NAME = "images";
    public static final String GAME_ATLAS_NAME = "imageAssets";

    public static final String PREFERENCES_NAME = "rodkast_prefs";
}
