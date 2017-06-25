package net.asg.game.providers;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class AssetsManager {
    private AssetManager manager;

    public AssetsManager() {
        this.manager = new AssetManager();
    }

    AssetManager getManager(){
        return manager;
    }

    public void dispose(){
        manager.dispose();
    }
}