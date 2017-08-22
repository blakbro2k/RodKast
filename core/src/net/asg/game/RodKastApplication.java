package net.asg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import net.asg.game.menu.ExitDialog;
import net.asg.game.providers.AssetsManager;
import net.asg.game.screens.HomeScreen;
import net.asg.game.screens.PlayListScreen;
import net.asg.game.screens.PodPlayerScreen;
import net.asg.game.screens.LoadingScreen;
import net.asg.game.screens.RodKastScreenAdapter;
import net.asg.game.utils.AudioUtils;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.XMLHandler;

import java.net.MalformedURLException;
import java.util.Stack;

public class RodKastApplication extends Game {
	private AssetsManager assetsManager;

	private XMLHandler xmlHandler;

	private FPSLogger fpsLog;

	private HomeScreen homeScreen;
	private PlayListScreen playListScreen;
	private PodPlayerScreen podPlayerScreen;
	private LoadingScreen loadingScreen;

	private GameEventListener gameEventListener;

    private Stack<RodKastScreenAdapter> screenStack;
    private ExitDialog exitDialog;
	private RodKastScreenAdapter currentScreen = null;

    private String TAG = "RodKastApplication";

    public RodKastApplication(GameEventListener gameEventListener){
		this.gameEventListener = gameEventListener;
	}

	@Override
	public void create() {
        //TODO: add log submittion handler
        assetsManager = new AssetsManager();

		try {
			xmlHandler = new XMLHandler();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		fpsLog = new FPSLogger();
		fpsLog.log();

		gotoLoadingScreen();
    }

	@Override
	public void dispose() {
        super.dispose();

		//disposed all objects that are Disposable
		Utils.disposeObjects(homeScreen,
				playListScreen,
				podPlayerScreen,
				assetsManager,
				xmlHandler);

		fpsLog = null;
        currentScreen = null;
        screenStack = null;
        exitDialog = null;
        gameEventListener = null;
		AudioUtils.getInstance().dispose();
    }

	public void render() {
        super.render();
		fpsLog.log();
    }

    public void gotoHomeScreen() {
		if(homeScreen == null){
			homeScreen = new HomeScreen(this);
		}
        setRodKastScreen(homeScreen);
    }

	public void gotoPlayListScreen() {
        if(playListScreen == null){
			playListScreen = new PlayListScreen(this);
		}
        setRodKastScreen(playListScreen);
    }

    public void gotoPodPlayerScreen() {
		if(podPlayerScreen == null){
			podPlayerScreen = new PodPlayerScreen(this);
		}
        setRodKastScreen(podPlayerScreen);
	}

	public void gotoLoadingScreen() {
		if(loadingScreen == null){
			loadingScreen = new LoadingScreen(this);
		}
		setRodKastScreen(loadingScreen);
	}

	private void setRodKastScreen(RodKastScreenAdapter screen){
        setScreen(screen);
        setCurrentScreen(screen);
    }

	public void pushScreen(RodKastScreenAdapter screen){
        if(screenStack == null){
			screenStack = new Stack<>();
		}

		if(!isSameScreen(screen)){
			screenStack.push(screen);
		}
	}

	private boolean isSameScreen(RodKastScreenAdapter screen) {
        return screenStack != null && !screenStack.isEmpty() && screen.equals(screenStack.peek());
	}

	private void setCurrentScreen(RodKastScreenAdapter screen) {
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
            Skin homeScreenSkin = getAssetsManager().getSkinProvider().getDefaultUISkin();
			exitDialog = new ExitDialog(MessageCatalog.BACK_BUTTON_MSG, homeScreenSkin);
		}
		return exitDialog;
	}

	public AssetsManager getAssetsManager(){
		return assetsManager;
	}

	public GameEventListener getGameEvenListener() {
		return gameEventListener;
	}

	public XMLHandler getXMLHandler() {return xmlHandler;}

	//public void getDebug(){	}
}
