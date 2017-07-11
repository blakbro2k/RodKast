package net.asg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

import net.asg.game.providers.AssetsManager;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.screens.HomeScreen;
import net.asg.game.screens.PlayListScreen;
import net.asg.game.screens.PodPlayerScreen;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class RodKastApplication extends Game {
	private AssetsManager assetsManager;
	private ImageProvider imageProvider;
	private SoundProvider soundProvider;
	private FPSLogger fpsLog;

	private HomeScreen homeScreen;
	private PlayListScreen playListScreen;
	private PodPlayerScreen podPlayerScreen;

	private List<Screen> screens;

	private GameEventListener gameEventListener;

	public RodKastApplication(GameEventListener gameEventListener){
		this.gameEventListener = gameEventListener;
	}

	@Override
	public void create() {
		assetsManager = new AssetsManager();
		imageProvider = new ImageProvider(assetsManager);
		soundProvider = new SoundProvider(assetsManager);

		fpsLog = new FPSLogger();
		fpsLog.log();

		screens = new ArrayList<Screen>();

		gotoHomeScreen();
	}

	@Override
	public void dispose() {
		super.dispose();

		//disposed all objects that are Disposable
		Util.disposeObjects(homeScreen, playListScreen, podPlayerScreen, assetsManager, imageProvider, soundProvider);

		fpsLog = null;
		gameEventListener = null;
		screens = null;
	}

	public void render() {
		super.render();
		fpsLog.log();
	}

    public void gotoHomeScreen() {
		if(homeScreen == null){
			homeScreen = new HomeScreen(this);
			screens.add(homeScreen);
		}
        setScreen(homeScreen);
    }

    public void gotoPlayListScreen() {
		if(playListScreen == null){
			playListScreen = new PlayListScreen(this);
			screens.add(playListScreen);
		}
        setScreen(playListScreen);
    }

    public void gotoPodPlayerScreen() {
		if(podPlayerScreen == null){
			podPlayerScreen = new PodPlayerScreen(this);
			screens.add(podPlayerScreen);
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

	public GameEventListener getGameEvenListener() {
		return gameEventListener;
	}
}
