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

	private HomeScreen homeScreen;
	private PlayListScreen playListScreen;
	private PodPlayerScreen podPlayerScreen;

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
		if(homeScreen == null){
			homeScreen = new HomeScreen(this);
		}
        setScreen(homeScreen);
    }

    public void gotoPlayListScreen() {
		if(playListScreen == null){
			playListScreen = new PlayListScreen(this);
		}
        setScreen(playListScreen);
    }

    public void gotoPodPlayerScreen() {
		if(podPlayerScreen == null){
			podPlayerScreen = new PodPlayerScreen(this);
		}
        setScreen(podPlayerScreen);
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
