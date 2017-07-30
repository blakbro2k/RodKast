package net.asg.game.providers;

import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Blakbro2k on 7/30/2017.
 */

public class SkinProvider implements Disposable {
    private AssetsManager assetsManager;

    public SkinProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
    }

    @Override
    public void dispose() {

    }
}
