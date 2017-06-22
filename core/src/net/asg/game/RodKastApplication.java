package net.asg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

import net.asg.game.providers.AssetsManager;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.screens.HomeScreen;
import net.asg.game.screens.PlayListScreen;
import net.asg.game.screens.PodPlayerScreen;

public class RodKastApplication extends Game {
	private AssetsManager assetsManager;
	private ImageProvider imageProvider;
	private SoundProvider soundProvider;
	private FPSLogger fpsLog;


	public RodKastApplication(){

	}

	@Override
	public void create() {
		assetsManager = new AssetsManager();
		imageProvider = new ImageProvider(getAssetsManager());
		soundProvider = new SoundProvider(getAssetsManager());

		fpsLog = new FPSLogger();
		fpsLog.log();

        gotoHomeScreen();
	}

	@Override
	public void dispose() {
		super.dispose();
		assetsManager.dispose();
	}

	public void render() {
		super.render();
		fpsLog.log();
	}

    public void gotoHomeScreen() {
        setScreen(new HomeScreen(this));
    }

    public void gotoPlayListScreen() {
        setScreen(new PlayListScreen(this));
    }

    public void gotoPodPlayerScreen() {
        setScreen(new PodPlayerScreen(this));
    }

	public AssetsManager getAssetsManager(){
		return assetsManager;
	}

	public ImageProvider getImageProvider(){
		return imageProvider;
	}

	public SoundProvider getSoundProvider() {
		return soundProvider;
	}
}
