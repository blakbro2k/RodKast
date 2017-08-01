package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.providers.AssetsManager;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SkinProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.RodkastEpisode;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by Blakbro2k on 7/26/2017.
 */

public class RodkastStageAdapter extends Stage {
    protected static final int BANNER_SIZE = 50;
    //protected static final String BANNER_SIZE = ghvc   bhgn
    ImageProvider imageProvider;
    SoundProvider soundProvider;
    SkinProvider skinProvider;
    AssetsManager manager;

    private Dialog loadingDialog;

    protected RodKastApplication app;
    private XMLHandler xmlHandler;
    private List<RodkastEpisode> episodeList;

    Skin defaultSkin;

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

        OrthographicCamera camera = null;
        Utils.setUpCamera(camera);
        Gdx.input.setCatchBackKey(true);
    }

    private void initializeStage() {
        manager.loadPreAssets();

        defaultSkin = skinProvider.getRodKastUISkin();
    }

    void loadAssets(){
        System.out.println("enter load Assets");
        if(loadingDialog == null){
            loadingDialog = new Dialog("Loading...", defaultSkin);
        }

        if(xmlHandler == null){
            xmlHandler = new XMLHandler();
        }

        loadingDialog.show(this);

        manager.loadPostAssets();
        episodeList = xmlHandler.getCompleteEpisodesList();

        loadingDialog.hide();
        System.out.println("exit load Assets");
    }

    public void setInputProcessor(){
        Gdx.input.setInputProcessor(this);
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getGameEvenListener().backButton(app);
        }
        return true;
    }

    List<RodkastEpisode> getEpisodelist(){
        System.out.println("enter getEpisodelist");
        System.out.println("episodeList: " + episodeList);

        if(episodeList == null){
            loadAssets();
        }
        System.out.println("exit getEpisodelist");

        return episodeList;
    }


    public int getBannerOffSet(){
        return (GlobalConstants.VIEWPORT_HEIGHT - BANNER_SIZE * 2);
    }
}