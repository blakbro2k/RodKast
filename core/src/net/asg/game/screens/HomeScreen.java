package net.asg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeScreen extends RodKastScreenAdapter {
    public HomeScreen(RodKastApplication app) {
        if(this.app == null) {
            this.app = app;
        }
        stage = new HomeStage(app);
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        //Gdx.gl.glClearColor(1f, 0.271f, 0f, 1f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClear(GL30.GL_Color);

        //Update the stage
        stage.draw();
        stage.act(delta);
    }
}
