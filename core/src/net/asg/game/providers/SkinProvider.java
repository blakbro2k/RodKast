package net.asg.game.providers;

import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 7/30/2017.
 */

public class SkinProvider implements Disposable {
    private AssetsManager assetsManager;
    private Skin defaultSkin;
    private Skin shadeSkin;

    public SkinProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
    }

    @Override
    public void dispose() {
        Utils.disposeObjects(defaultSkin,shadeSkin);
    }

    void setUpSkinLoaders(){
        SkinLoader.SkinParameter skinParams = new SkinLoader.SkinParameter(GlobalConstants.DEFAULT_UISKIN_ATLAS);
        SkinLoader.SkinParameter shadeSkinParams = new SkinLoader.SkinParameter(GlobalConstants.SHADE_UISKIN_ATLAS);

        assetsManager.getManager().load(GlobalConstants.SHADE_UISKIN_JSON, Skin.class, shadeSkinParams);
        assetsManager.getManager().load(GlobalConstants.DEFAULT_UISKIN_JSON, Skin.class, skinParams);

    }

    public Skin getDefaultUISkin() {
        if(assetsManager.getManager().isLoaded(GlobalConstants.DEFAULT_UISKIN_JSON)) {
            defaultSkin = assetsManager.getManager().get(GlobalConstants.DEFAULT_UISKIN_JSON);
        }
        return defaultSkin;
    }

    public Skin getRodKastUISkin() {
        if(assetsManager.getManager().isLoaded(GlobalConstants.SHADE_UISKIN_JSON)) {
            shadeSkin = assetsManager.getManager().get(GlobalConstants.SHADE_UISKIN_JSON);
        }
        return shadeSkin;
    }
}
