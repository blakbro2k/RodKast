package net.asg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.LoadingStage;

/**
 * Created by eboateng on 8/9/2017.
 */

public class LoadingScreen extends RodKastScreenAdapter {
    private long lastUpdate = 0L;
    private float remainingPercentage = 1.0f;

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

           //Interpolation

        if(stage != null){
            if(!stage.assets().isUpdateDone()){
                ((LoadingStage) stage).update(stage.assets().getProgress());
            } else if(!stage.isXmlLoaded()) {
                try{
                    stage.loadXmlData();
                } catch (GdxRuntimeException e){
                    stage.displayErrorMessage(e.getMessage());
                }
            } else {
                if (System.currentTimeMillis() - lastUpdate > 7L) {
                    ((LoadingStage) stage).timer(remainingPercentage);

                    remainingPercentage -= 0.01f;
                    lastUpdate = System.currentTimeMillis();

                    if (remainingPercentage <= 0.0f) {
                        //remainingPercentage = 1.0f;
                        app.gotoHomeScreen();
                    }
                }
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
