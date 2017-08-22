package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.screens.RodKastScreenAdapter;
import net.asg.game.utils.parser.RodkastEpisode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class Utils {
    //prevent instantiation
    private Utils(){}

    /**
     * Takes exit Dialog from a HomeStage and executes app exit only on second confirm from user
     * @param
     */
    public static void backButton(RodKastApplication app) {
        if(app != null){
            if (app.isLastScreen()){
                processExit(app);
            } else {
                RodKastScreenAdapter screen = app.popScreen();
                if(screen != null){
                    screen.gotoScreen();
                }
            }
        }
    }

    /**
     *
     * @param app
     */
    private static void processExit(RodKastApplication app){
        ExitDialog exitDialog = app.getExitDialog();
        RodKastScreenAdapter screen = app.getCurrentScreen();

        if(exitDialog != null && screen != null) {
            if (exitDialog.isVisible()) {
                if (exitDialog.getCount() > 0) {
                    Gdx.app.exit();
                }
            }
            Stage stage = screen.getStage();
            if(stage != null){
                exitDialog.show(stage);
            }
            exitDialog.increment();
        }
    }

    /**
     * Disposes of all objects that implements {@code Disposable}
     * @param objs
     */
    public static void disposeObjects(Disposable... objs){
        if(objs != null){
            for(Disposable obj : objs){
                if(obj != null){
                    obj.dispose();
                }
            }
        }
    }

    /**
     *
     * @param camera
     */
    //TODO: further abstract class by passing x, y, z, and setting defaults if null as constants
    public static void setUpCamera(OrthographicCamera camera) {
        if(camera == null){
            camera = new OrthographicCamera(GlobalConstants.VIEWPORT_WIDTH, GlobalConstants.VIEWPORT_HEIGHT);
        }

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    /**
     *
     * @param numMonth
     * @return
     */
    public static String getThreeLetterMonth(int numMonth){
        String ret;
        switch(numMonth){
            case 0:
                ret = "JAN";
                break;
            case 1:
                ret = "FEB";
                break;
            case 2:
                ret = "MAR";
                break;
            case 3:
                ret = "APR";
                break;
            case 4:
                ret = "MAY";
                break;
            case 5:
                ret = "JUN";
                break;
            case 6:
                ret = "JUL";
                break;
            case 7:
                ret = "AUG";
                break;
            case 8:
                ret = "SEP";
                break;
            case 9:
                ret = "OCT";
                break;
            case 10:
                ret = "NOV";
                break;
            case 11:
                ret = "DEC";
                break;
            default:
                ret = "";
                break;
        }
        return ret;
    }

    /**
     *
     * @param title
     * @return
     */
    public static String cleanTitle(String title) {
        String ret = "";
        if (title != null){
            ret = title.replace("#8211","-")
                    .replace("#8217","'")
                    .replace("#8220","\"")
                    .replace("#8221","\"")
                    .replace("#8230","...")
                    .replace("#038","&");
        }
        return ret;
    }

    /**
     *
     * @param attribute
     * @return
     */
    public static float atof(String attribute) {
        if (attribute != null){
            return Float.parseFloat(attribute);
        }
        return 0;
    }

    public static String getTitleFromEpisode(RodkastEpisode episode) {
        String string = "";
        if(episode != null){
            string = cleanTitle(episode.getTitle());
        }
        return string;
    }

    public static void closeInputStream(InputStream is) throws GdxRuntimeException{
        closeStream(is);
    }

    public static void closeOutputStream(OutputStream os) throws GdxRuntimeException{
        closeStream(os);
    }

    private static void closeStream(Object o) throws GdxRuntimeException{
        if(o != null){
            try {
                if(o instanceof InputStream){
                    ((InputStream)o).close();
                }
                if(o instanceof OutputStream){
                    ((OutputStream)o).close();
                }
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            }
        }
        throw new GdxRuntimeException("Object stream must be InputStream or OutputStream");
    }
}
