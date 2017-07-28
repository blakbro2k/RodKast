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
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;
import net.asg.game.utils.Utils;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;

/**
 * Created by Blakbro2k on 7/26/2017.
 */

public class RodkastStageAdapter extends Stage {
    protected ImageProvider imageProvider;
    protected SoundProvider soundProvider;
    protected OrthographicCamera camera;
    protected RodKastApplication app;
    private XMLHandler xmlHandler;

    public RodkastStageAdapter(RodKastApplication app) {
        super(new ScalingViewport(Scaling.stretch, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,
                new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)));
        this.app = app;
        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();
        this.xmlHandler = app.getXMLHandler();

        Utils.setUpCamera(camera);
        Gdx.input.setCatchBackKey(true);
    }

    protected void loadAssets(){
        imageProvider.pauseUntilLoaded();

        Dialog loadingDialog = new Dialog("Loading...", imageProvider.getShadeUISkin());
        loadingDialog.show(this);

        xmlHandler = new XMLHandler();

        try {
            xmlHandler.getTotalRssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadingDialog.hide();
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getGameEvenListener().backButton(app);
        }
        return true;
    }
}