package net.asg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.RodkastStageAdapter;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public abstract class RodKastScreenAdapter implements Screen, Disposable{
    protected RodkastStageAdapter stage;
    protected RodKastApplication app;

    @Override
    public void show() {
        if(stage != null){
            stage.setInputProcessor();
        }
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(stage != null){
            stage.act(delta);
            //Update the stage
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if(stage != null){
            stage.dispose();
        }
    }

    public Stage getStage(){
        return stage;
    }

    public abstract void gotoScreen();
}
