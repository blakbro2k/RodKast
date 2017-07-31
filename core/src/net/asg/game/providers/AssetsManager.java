package net.asg.game.providers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class AssetsManager implements Disposable{
    private final AssetManager manager;
    private final ImageProvider imageProvider;
    private final SoundProvider soundProvider;
    private final SkinProvider skinProvider;
    private float percentLoaded;

    public AssetsManager() {
        this.manager = new AssetManager();
        this.skinProvider = new SkinProvider(this);
        this.soundProvider = new SoundProvider(this);
        this.imageProvider = new ImageProvider(this);
        percentLoaded = 0;
    }

    AssetManager getManager(){
        return manager;
    }

    @Override
    public void dispose(){
        Utils.disposeObjects(manager,
                imageProvider,
                soundProvider,
                skinProvider);
    }

    public void loadPostAssets(){
        queueImages();
        queueSounds();
        manager.finishLoading();
    }

    public void loadPreAssets(){
        queueSkins();
        manager.finishLoading();
    }

    public float getProgress(){
        percentLoaded = Interpolation.linear.apply(percentLoaded, manager.getProgress(), 0.05f);
        return percentLoaded;
    }

    private void queueSounds() {
        soundProvider.setUpSoundLoaders();
    }

    private void queueImages() {
        imageProvider.setUpImageLoaders();
    }

    private void queueSkins() {
        skinProvider.setUpSkinLoaders();
    }

    public ImageProvider getImageProvider(){
        return imageProvider;
    }

    public SoundProvider getSoundProvider() {
        return soundProvider;
    }

    public SkinProvider getSkinProvider() {
        return skinProvider;
    }

}