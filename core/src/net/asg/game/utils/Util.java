package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.menu.ExitDialog;
import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class Util {
    //prevent instantiation
    private Util(){}

    /**
     * Takes exit Dialog from a HomeStage and executes app exit only on second confirm from user
     * @param stage
     */
    public static void backButtonUtil(HomeStage stage) {
        if(stage != null){
            ExitDialog exitDialog = stage.getExitDialog();

            if(exitDialog != null){
                if(exitDialog.isVisible()){
                    if(exitDialog.getCount() > 0){
                        Gdx.app.exit();
                    }
                }
                exitDialog.show(stage);
                exitDialog.increment();
            }
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
}
