package net.asg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import net.asg.game.menu.ExitDialog;
import net.asg.game.providers.AssetsManager;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.screens.HomeScreen;
import net.asg.game.screens.PlayListScreen;
import net.asg.game.screens.PodPlayerScreen;
import net.asg.game.screens.RodKastScreenAdapter;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.XMLHandler;

import java.util.Stack;

public class RodKastApplication extends Game {
	private AssetsManager assetsManager;
	private ImageProvider imageProvider;
	private SoundProvider soundProvider;

	private XMLHandler xmlHandler;

	private FPSLogger fpsLog;

	private HomeScreen homeScreen;
	private PlayListScreen playListScreen;
	private PodPlayerScreen podPlayerScreen;

	private GameEventListener gameEventListener;

	public Stack<RodKastScreenAdapter> screenStack;
	private Skin homeScreenSkin;
	private ExitDialog exitDialog;
	private RodKastScreenAdapter currentScreen = null;

	public RodKastApplication(GameEventListener gameEventListener){
		this.gameEventListener = gameEventListener;
	}

	@Override
	public void create() {
		assetsManager = new AssetsManager();
		imageProvider = new ImageProvider(assetsManager);
		soundProvider = new SoundProvider(assetsManager);
		xmlHandler = new XMLHandler();

		fpsLog = new FPSLogger();
		fpsLog.log();


		gotoHomeScreen();
		setCurrentScreen(homeScreen);
	}

	@Override
	public void dispose() {
		super.dispose();

		//disposed all objects that are Disposable
		Utils.disposeObjects(homeScreen,
				playListScreen,
				podPlayerScreen,
				assetsManager,
				imageProvider,
				soundProvider,
				xmlHandler);

		fpsLog = null;
		gameEventListener = null;
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

	public void pushScreen(RodKastScreenAdapter screen){
		if(screenStack == null){
			screenStack = new Stack<>();
		}
		screenStack.push(screen);
	}

	public void setCurrentScreen(RodKastScreenAdapter screen) {
			currentScreen = screen;
	}

	public RodKastScreenAdapter getCurrentScreen(){
		return currentScreen;
	}

	public RodKastScreenAdapter popScreen(){
		RodKastScreenAdapter returnScreen = currentScreen;
		if(!isLastScreen()){
			returnScreen = screenStack.pop();
		}
		return returnScreen;
	}

	public boolean isLastScreen(){
		return (screenStack == null) || screenStack.size() == 0;
	}

	public ExitDialog getExitDialog(){
		if(exitDialog == null){
			homeScreenSkin = imageProvider.getDefaultUISkin();
			exitDialog = new ExitDialog("Do you really want to exit?", homeScreenSkin);
		}
		return exitDialog;
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

	public XMLHandler getXMLHandler() {return xmlHandler;}
}
