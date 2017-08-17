package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.providers.AssetsManager;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.MenuProvider;
import net.asg.game.providers.SkinProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastChannel;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Blakbro2k on 7/26/2017.
 */

public class RodkastStageAdapter extends Stage {
    protected static final int BANNER_SIZE = 50;

    ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private SkinProvider skinProvider;
    private AssetsManager manager;
    RodKastApplication app;
    private XMLHandler xmlHandler;

    private OrthographicCamera camera;
    MenuProvider menuProvider;

    private Dialog loadingDialog;
    private Dialog errorDialog;

    private RodkastChannel rssChannel;

    Skin defaultSkin;

    //protected Skin defaultSkin;
    Label.LabelStyle defaultScreenLabelStyle;
    Label.LabelStyle titleScreenLabelStyle;
    Label.LabelStyle titlePlainScreenLabelStyle;

    public RodkastStageAdapter(RodKastApplication app) {
        super(new ScalingViewport(Scaling.stretch, GlobalConstants.VIEWPORT_WIDTH, GlobalConstants.VIEWPORT_HEIGHT,
                new OrthographicCamera(GlobalConstants.VIEWPORT_WIDTH, GlobalConstants.VIEWPORT_HEIGHT)));
        this.app = app;
        this.imageProvider = app.getAssetsManager().getImageProvider();
        this.soundProvider = app.getAssetsManager().getSoundProvider();
        this.skinProvider = app.getAssetsManager().getSkinProvider();
        this.xmlHandler = app.getXMLHandler();
        this.manager = app.getAssetsManager();

        initializeStage();

        Utils.setUpCamera(camera);
        Gdx.input.setCatchBackKey(true);
    }

    private void initializeStage() {
        manager.loadPreAssets();
        defaultSkin = skinProvider.getRodKastUISkin();
        menuProvider = new MenuProvider(defaultSkin);

        defaultScreenLabelStyle = menuProvider.getDefaultLabelStyle();
        titleScreenLabelStyle = menuProvider.getTitleLableStyle();
        titlePlainScreenLabelStyle = menuProvider.getTitlePlainLableStyle();
    }

    void loadAssets(){
        if(loadingDialog == null){
            System.out.println("loading....");
            loadingDialog = new Dialog(MessageCatalog.LOADING_MSG, defaultSkin);
        }
        loadingDialog.show(this);

        manager.loadPostAssets();
        try{
            if(xmlHandler == null){
                xmlHandler = new XMLHandler();
            }
            rssChannel = xmlHandler.buildChannel();
        } catch (IOException e) {
            if(errorDialog == null){
                errorDialog = new Dialog(e.getMessage(), defaultSkin);
                errorDialog.show(this);
                System.out.println(e.getMessage() + " is handeled");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        loadingDialog.hide();
    }

    public void setInputProcessor(){
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getGameEvenListener().backButton(app);
        }
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);

        ExitDialog exitDialog = app.getExitDialog();
        if(exitDialog != null && exitDialog.isVisible()){
            exitDialog.resetCount();
        }
        return true;
    }

    List<RodkastEpisode> getEpisodelist(){
        if(rssChannel == null){
            loadAssets();
        }
        return rssChannel.getEpisodes();
    }

    public int getBannerOffSet(){
        return (GlobalConstants.VIEWPORT_HEIGHT - BANNER_SIZE * 2);
    }

    @Override
    public void dispose(){
        Utils.disposeObjects(menuProvider,
                rssChannel,
                defaultSkin);
        camera = null;
    }
}