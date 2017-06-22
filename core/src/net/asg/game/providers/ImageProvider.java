package net.asg.game.providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import net.asg.game.exceptions.AssetNotLoadedException;
import net.asg.game.utils.Constants;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class ImageProvider {
    private AssetsManager assetsManager;

    public ImageProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
        loadImages();
    }

    public void loadImages(){
        assetsManager.getManager().load(Constants.BAD_LOGIC_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_PATH, BitmapFont.class);
        //defaultFont = new BitmapFont(Gdx.files.internal("data/default.fnt"), Gdx.files.internal("data/default.png"), false);
    }

    public BitmapFont defaultFont() throws AssetNotLoadedException{
        if(assetsManager.getManager().isLoaded(Constants.DEFAULT_FONT_PATH)) {
            return assetsManager.getManager().get(Constants.DEFAULT_FONT_PATH);
        }
        throw new AssetNotLoadedException(Constants.DEFAULT_FONT_PATH);
    }
}