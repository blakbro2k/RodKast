package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.screens.RodKastScreenAdapter;

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

    public static String getThreeLetterMonth(int numMonth){
        String ret = "";
        switch(numMonth){
            case 1:
                ret = "JAN";
                break;
            case 2:
                ret = "FEB";
                break;
            case 3:
                ret = "MAR";
                break;
            case 4:
                ret = "APR";
                break;
            case 5:
                ret = "MAY";
                break;
            case 6:
                ret = "JUN";
                break;
            case 7:
                ret = "JUL";
                break;
            case 8:
                ret = "AUG";
                break;
            case 9:
                ret = "SEP";
                break;
            case 10:
                ret = "OCT";
                break;
            case 11:
                ret = "NOV";
                break;
            case 12:
                ret = "DEC";
                break;
            default:
                break;
        }
        return ret;
    }
}
