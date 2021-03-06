package net.asg.game.providers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

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

    @Override
    public void dispose() {

    }

    public void setUpSoundLoaders() {

    }
}
