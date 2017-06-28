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
import net.asg.game.menu.ExitDialog;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends Stage {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private static final int HOMESTAGE_TOP_MENU_HEIGHT = 100;
    private static final int HOMESTAGE_BOTTOM_MENU_HEIGHT = 100;

    private ImageProvider imageProvider;
    private SoundProvider soundProvider;
    private OrthographicCamera camera;
    private RodKastApplication app;

    private ExitDialog exitDialog;

    //TODO: callButton
    //TODO: shopButton
    //TODO: AD Resolver
    //TODO: Podcast Scroller
    //TODO: Social Medial
    //TODO: SettingButton

    public HomeStage(RodKastApplication app){
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        this.app = app;

        this.imageProvider = app.getImageProvider();
        this.soundProvider = app.getSoundProvider();

        imageProvider.pauseUntilLoadedImages();
        setUpCamera();
        setUpStageTable();
        setUpMenuItems();

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
    }

    private void setUpMenuItems() {
        exitDialog = new ExitDialog("Do you really want to exit?",imageProvider.getDefaultUISkin());
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

        setUpTopRowButtons(table, defaultStyle);
        setUpTopBodySection(table, defaultStyle);
        setUpTopSocialSection(table, defaultStyle);
        setUpTopAdSection(table, defaultStyle);

        addActor(table);
    }

    private void setUpTopAdSection(Table table, Label.LabelStyle defaultStyle) {
        Label adLabel = new Label("AD SECTION",defaultStyle);

        table.add(adLabel).width(VIEWPORT_WIDTH).height(HOMESTAGE_BOTTOM_MENU_HEIGHT);
    }

    private void setUpTopSocialSection(Table table, Label.LabelStyle defaultStyle) {
        Label socialLabel = new Label("SOCIAL SECTION",defaultStyle);

        table.add(socialLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .3f);
        table.row();
    }

    private void setUpTopBodySection(Table table, Label.LabelStyle defaultStyle) {
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);

        table.add(bodyLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .7f);
        table.row();
    }

    private int getMenuOffSet(){
        return HOMESTAGE_TOP_MENU_HEIGHT + HOMESTAGE_BOTTOM_MENU_HEIGHT;
    }

    private void setUpTopRowButtons(Table table, Label.LabelStyle defaultStyle) {
        Label headingLabel = new Label("TOP SECTION",defaultStyle);

        table.add(headingLabel).width(VIEWPORT_WIDTH).height(HOMESTAGE_TOP_MENU_HEIGHT);
        table.row();
    }

    public ExitDialog getExitDialog(){
        return exitDialog;
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getResolver().backButton(this);
        }
        return true;
    }
}
