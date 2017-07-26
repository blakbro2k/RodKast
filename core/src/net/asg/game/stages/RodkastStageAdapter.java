package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 7/26/2017.
 */

public class RodkastStageAdapter extends Stage {
    protected ImageProvider imageProvider;
    protected SoundProvider soundProvider;
    protected OrthographicCamera camera;
    protected RodKastApplication app;

    protected Skin defaultSkin;

    protected Label.LabelStyle homeScreenLabelStyle;

    public RodkastStageAdapter(RodKastApplication app) {
        super(new ScalingViewport(Scaling.stretch, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,
                new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT)));
        this.app = app;
        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();

        imageProvider.pauseUntilLoaded();
        defaultSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        Utils.setUpCamera(camera);
        Gdx.input.setCatchBackKey(true);
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getGameEvenListener().backButton(app);
        }
        return true;
    }
}