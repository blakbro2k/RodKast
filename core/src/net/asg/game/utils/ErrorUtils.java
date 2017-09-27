package net.asg.game.utils;

import com.badlogic.gdx.utils.GdxRuntimeException;

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
    }

    private void popMessage(String message){
        RodKastScreenAdapter screen = application.getCurrentScreen();
        RodkastStageAdapter stage;

        if(screen != null){
            stage = screen.getStage();
            if(stage != null){
                stage.displayErrorMessage(message);
            }
        }
    }
}
