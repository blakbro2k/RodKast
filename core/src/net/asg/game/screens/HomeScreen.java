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
        super();

        if(this.app == null) {
            this.app = app;
        }

        stage = new HomeStage(app);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //Clear the screen
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        //Update the stage
        stage.draw();
    }

    @Override
    public void gotoScreen() {
        app.pushScreen(this);
        app.setCurrentScreen(this);
        app.gotoHomeScreen();
    }
}
