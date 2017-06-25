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
import net.asg.game.utils.ActionResolver;

import java.util.ArrayList;
import java.util.Iterator;
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

	private ActionResolver resolver;

	public RodKastApplication(ActionResolver resolver){
		this.resolver = resolver;
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
		assetsManager.dispose();
		imageProvider.dispose();

		//disposed all screen that were used this session
		disposeScreens(screens);

		fpsLog = null;
		resolver = null;
		screens = null;
	}

	private void disposeScreens(List<Screen> screens) {
		if(screens != null){
			Iterator<Screen> iter = screens.iterator();
			if(iter != null){
				while(iter.hasNext()){
					Screen screen = iter.next();
					if(screen != null){
						screen.dispose();
					}
				}
			}
		}
	}

	public void render() {
		super.render();
		fpsLog.log();
	}

    public void gotoHomeScreen() {
		if(homeScreen == null){
			screens.add(homeScreen);
			homeScreen = new HomeScreen(this);
		}
        setScreen(homeScreen);
    }

    public void gotoPlayListScreen() {
		if(playListScreen == null){
			screens.add(playListScreen);
			playListScreen = new PlayListScreen(this);
		}
        setScreen(playListScreen);
    }

    public void gotoPodPlayerScreen() {
		if(podPlayerScreen == null){
			screens.add(podPlayerScreen);
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

	public ActionResolver getResolver() {
		return resolver;
	}
}
