package net.asg.game.providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import net.asg.game.utils.Constants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class ImageProvider {
    private AssetsManager assetsManager;
    private Skin defaultSkin;

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
        //assetsManager.getManager().load(Constants.DEFAULT_UISKIN_PATH, Skin.class);
        //getDefaultFont = new BitmapFont(Gdx.files.internal("data/default.fnt"), Gdx.files.internal("data/default.png"), false);
    }

    public void pauseUntilLoadedImages(){
        assetsManager.getManager().finishLoading();
    }

    public BitmapFont getDefaultFont() {
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
        BitmapFont font = getDefaultFont();
        if(font != null){
            return new Label.LabelStyle(font, Color.RED);
        }
        return null;
    }

    public Skin getDefaultUISkin() {
        if(defaultSkin == null){
            defaultSkin = new Skin(Gdx.files.internal(Constants.DEFAULT_UISKIN_PATH));
        }
        return defaultSkin;
    }
}