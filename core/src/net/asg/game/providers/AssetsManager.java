package net.asg.game.providers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class AssetsManager implements Disposable{
    private final AssetManager manager;

    public AssetsManager() {
        this.manager = new AssetManager();
    }

    AssetManager getManager(){
        return manager;
    }

    @Override
    public void dispose(){
        manager.dispose();
    }
}