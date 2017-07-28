package net.asg.game.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.RodKastApplication;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public abstract class RodKastScreenAdapter implements Screen, Disposable{
    protected Stage stage;
    protected RodKastApplication app;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
