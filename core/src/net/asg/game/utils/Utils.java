package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.screens.RodKastScreenAdapter;
import net.asg.game.stages.HomeStage;

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
    public static void backButtonUtil(RodKastApplication app) {
        if(app != null){
            if (app.isLastScreen()){
                processExit(app);
            } else {
                RodKastScreenAdapter screen = app.popScreen();
                screen.gotoScreen();
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
            exitDialog.show(screen.getStage());
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
    public static void setUpCamera(OrthographicCamera camera) {
        if(camera == null){
            camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        }

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }
}
