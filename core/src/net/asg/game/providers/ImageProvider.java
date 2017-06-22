package net.asg.game.providers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.exceptions.AssetNotLoadedException;
import net.asg.game.utils.Constants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class ImageProvider {
    private AssetsManager assetsManager;

    public ImageProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
        setUpImageLoaders();
    }

    private void update(){
        assetsManager.getManager().update();
    }

    public void setUpImageLoaders(){
        assetsManager.getManager().load(Constants.BAD_LOGIC_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_PATH, BitmapFont.class);
        //defaultFont = new BitmapFont(Gdx.files.internal("data/default.fnt"), Gdx.files.internal("data/default.png"), false);

        update();
    }

    public void pauseUntilLoadedImages(){
        assetsManager.getManager().finishLoading();
    }

    public BitmapFont defaultFont() {
        if(assetsManager.getManager().isLoaded(Constants.DEFAULT_FONT_PATH)) {
            return assetsManager.getManager().get(Constants.DEFAULT_FONT_PATH);
        }
        return null;
    }

    public Texture getBadLogicImage() {
        if(assetsManager.getManager().isLoaded(Constants.BAD_LOGIC_IMAGE_PATH)) {
            return assetsManager.getManager().get(Constants.BAD_LOGIC_IMAGE_PATH);
        }
        return null;
    }

    public Label.LabelStyle getDefaultLableStyle(){
        BitmapFont font = defaultFont();
        if(font != null){
            return new Label.LabelStyle(font, Color.RED);
        }
        return null;
    }
}