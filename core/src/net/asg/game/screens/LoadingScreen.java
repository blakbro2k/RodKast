package net.asg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.LoadingStage;
import net.asg.game.utils.ErrorUtils;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by eboateng on 8/9/2017.
 */

public class LoadingScreen extends RodKastScreenAdapter {
    private long lastUpdate = 0L;
    private float remainingPercentage = 1.0f;

    public LoadingScreen(RodKastApplication app) {
        super();

        if (this.app == null) {
            this.app = app;
        }

        stage = new LoadingStage(app);
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Interpolation

            if (stage != null) {
                stage.act(delta);
                //Update the stage
                stage.draw();

                if (!stage.assets().isUpdateDone()) {
                    ((LoadingStage) stage).update(stage.assets().getProgress());
                } else if (!stage.isXmlLoaded()) {
                    try {
                        stage.loadXmlData();
                    } catch (Exception e) {
                        ErrorUtils.getInstance().showErrorPopup(e);
                    }
                    app.gotoHomeScreen();
                }
            }

    }

    @Override
    public void gotoScreen() {
        app.gotoLoadingScreen();
    }
}
