package net.asg.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.exceptions.AssetNotLoadedException;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends Stage {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

   //private RodKastApplication app;
    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private OrthographicCamera camera;

    public HomeStage(RodKastApplication app){
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        //this.app = app;
        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();

        setUpCamera();

        try {
            setUpStageTable();
        } catch (AssetNotLoadedException anle) {
            //e.printStackTrace();
            app.getAssetsManager().getManager().update();
            //setUpStageTable();
        }
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpStageTable() throws AssetNotLoadedException{
        Table table = new Table();
        table.debug();
        table.setPosition(0, 0);

        Label.LabelStyle headingStyle = new Label.LabelStyle(imageProvider.defaultFont(), Color.RED);
        Label headingLabel = new Label("TOP BANNER",headingStyle);
    }
}
