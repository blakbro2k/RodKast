package net.asg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.LoadingStage;

/**
 * Created by eboateng on 8/9/2017.
 */

public class LoadingScreen extends RodKastScreenAdapter {
        public LoadingScreen(RodKastApplication app) {
            super();

            if(this.app == null) {
                this.app = app;
            }

            stage = new LoadingStage(app);
        }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(stage != null){
            if(!stage.assets().finishedStepLoading()){
                ((LoadingStage) stage).update(stage.assets().getProgress());
            } else if(!stage.isXmlLoaded()) {
                try{
                    stage.loadXmlData();
                } catch (GdxRuntimeException e){
                    stage.displayErrorMessage(e.getMessage());
                }
            } else {
                app.gotoHomeScreen();
            }

            stage.act(delta);
            //Update the stage
            stage.draw();
        }
    }

    @Override
    public void gotoScreen() {
        app.gotoLoadingScreen();
    }
}
