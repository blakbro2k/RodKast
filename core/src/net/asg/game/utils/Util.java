package net.asg.game.utils;

import com.badlogic.gdx.Gdx;

import net.asg.game.menu.ExitDialog;
import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class Util {
    //prevent instantiation
    private Util(){}

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
}
