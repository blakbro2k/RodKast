package net.asg.game.providers;

import com.badlogic.gdx.graphics.Texture;

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
        assetsManager.getManager().load("", Texture.class);
    }
}