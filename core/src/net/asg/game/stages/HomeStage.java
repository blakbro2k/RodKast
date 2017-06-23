package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

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

    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private OrthographicCamera camera;

    public HomeStage(RodKastApplication app){
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
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
        table.setBounds(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        table.setWidth(VIEWPORT_WIDTH);

        table.setPosition(0,0);
        Label.LabelStyle defaultStyle = imageProvider.getDefaultLableStyle();
        Label headingLabel = new Label("TOP BANNER",defaultStyle);
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);
        Label socialLabel = new Label("SOCIAL SECTION",defaultStyle);
        Label adLabel = new Label("AD SECTION",defaultStyle);

        table.add(headingLabel).width(VIEWPORT_WIDTH).height(100);
        table.row();
        table.add(bodyLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - 200) * .7f);
        table.row();
        table.add(socialLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - 200) * .3f);
        table.row();
        table.add(adLabel).width(VIEWPORT_WIDTH).height(100);

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
