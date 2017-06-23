package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.asg.game.RodKastApplication;
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
        //super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
          //      new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        super(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        //this.app = app;

        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();

        imageProvider.pauseUntilLoadedImages();
        setUpCamera();
        setUpStageTable();

        Gdx.input.setInputProcessor(this);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpStageTable(){
        Table table = new Table();
        table.debug();
        //table.setBounds(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        table.setPosition(VIEWPORT_WIDTH / 2,VIEWPORT_HEIGHT /2);
        Label.LabelStyle defaultStyle = imageProvider.getDefaultLableStyle();
        Label headingLabel = new Label("TOP BANNER",defaultStyle);
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);
        Label socialLabel = new Label("SOCIAL SECTION",defaultStyle);
        Label adLabel = new Label("AD SECTION",defaultStyle);

        table.add(headingLabel);
        table.row();
        table.add(bodyLabel);
        table.row();
        table.add(socialLabel);
        table.row();
        table.add(adLabel);

        System.out.println("added: " + table);
        addActor(table);
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            Gdx.app.exit();
            //game.getActionResolver().backButton(game);
        }
        return true;
    }
}
