package net.asg.game.providers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.Constants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class SoundProvider implements Disposable {
    private AssetsManager assetsManager;

    public SoundProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
    }

    public void setUpEpisodeLoaders(String path){
        assetsManager.getManager().load(path, Music.class);
    }

    public void pauseUntilLoaded(){
        assetsManager.getManager().finishLoading();
    }


    @Override
    public void dispose() {

    }
}
