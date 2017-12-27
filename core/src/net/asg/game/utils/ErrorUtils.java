package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import net.asg.game.RodKastApplication;
import net.asg.game.screens.RodKastScreenAdapter;
import net.asg.game.stages.RodkastStageAdapter;

/**
 * Created by eboateng on 9/1/2017.
 */

public class ErrorUtils {
    private static ErrorUtils _ourInstance = new ErrorUtils();
    private RodKastApplication application;

    private ErrorUtils() {}

    public static ErrorUtils getInstance() {
        return _ourInstance;
    }

    public void setApplication(RodKastApplication application){
        this.application = application;
    }

    private boolean hasApplication(){
        return application != null;
    }

    private void validateUtil(){
        if(!hasApplication()){
            throw new GdxRuntimeException("An application must be set to send messages to");
        }
    }

    public void showErrorPopup(GdxRuntimeException e){
        validateUtil();

        String message;
        if(e != null){
            message = e.getMessage();
            if(message != null){
                popMessage(message);
            }
        }
    }

    public void showErrorPopup(Throwable t){
        validateUtil();

        String message;
        if(t != null){
            message = t.getMessage();
            if(message != null){
                popMessage(message);
            }
        }
        //throw new GdxRuntimeException(t);
    }

    private void popMessage(String message){
        RodKastScreenAdapter screen = application.getCurrentScreen();
        RodkastStageAdapter stage;

        if(screen != null){
            stage = screen.getStage();
            if(stage != null){
                stage.displayErrorMessage(message);
                closeMessageOnTimer();
            }
        }
    }

    private void hideMessage(){
        RodKastScreenAdapter screen = application.getCurrentScreen();
        RodkastStageAdapter stage;

        if(screen != null){
            stage = screen.getStage();
            if(stage != null){
                stage.hideErrorMessage();
            }
        }
    }

    private void closeMessageOnTimer(){
        final long splash_start_time = TimeUtils.millis();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                long splash_elapsed_time = TimeUtils.millis() - splash_start_time;
                if (splash_elapsed_time < GlobalConstants.ERROR_MSG_TIMEOUT) {
                    Timer.schedule(
                            new Timer.Task() {
                                @Override
                                public void run() {
                                    hideMessage();
                                }
                            }, (float) (GlobalConstants.ERROR_MSG_TIMEOUT - splash_elapsed_time) / 1000);
                } else {
                    hideMessage();
                }
            }
        });
    }
}
