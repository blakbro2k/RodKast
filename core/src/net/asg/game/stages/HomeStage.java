package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private Skin homeScreenSkin;
    private LabelStyle homeScreenLabelStyle;

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
        homeScreenSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        setUpCamera();
        setUpStageTable();
        setUpMenuItems();

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
    }

    private void setUpMenuItems() {
        exitDialog = new ExitDialog("Do you really want to exit?", homeScreenSkin);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpStageTable(){
        Table mainScreenTable = new Table();
        mainScreenTable.debug();
        mainScreenTable.setBounds(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainScreenTable.setWidth(VIEWPORT_WIDTH);
        mainScreenTable.setPosition(0,0);

        setUpTopRowButtons(mainScreenTable, homeScreenLabelStyle);
        setUpTopBodySection(mainScreenTable, homeScreenLabelStyle);
        setUpTopSocialSection(mainScreenTable, homeScreenLabelStyle);
        setUpTopAdSection(mainScreenTable, homeScreenLabelStyle);

        addActor(mainScreenTable);
    }

    private void setUpTopAdSection(Table table, LabelStyle defaultStyle) {
        Label adLabel = new Label("AD SECTION",defaultStyle);

        table.add(adLabel).height(HOMESTAGE_BOTTOM_MENU_HEIGHT);
    }

    private void setUpTopSocialSection(Table table, LabelStyle defaultStyle) {
        Label socialLabel = new Label("SOCIAL SECTION",defaultStyle);

        table.add(socialLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .3f);
        table.row();
    }

    private void setUpTopBodySection(Table table, LabelStyle defaultStyle) {
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);

        table.add(bodyLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .7f);
        table.row();
    }

    private int getMenuOffSet(){
        return HOMESTAGE_TOP_MENU_HEIGHT + HOMESTAGE_BOTTOM_MENU_HEIGHT;
    }

    private void setUpTopRowButtons(Table table, LabelStyle defaultStyle) {
        Label headingLabel = new Label("TOP SECTION",defaultStyle);

        //final ImageButton settingsGameButton = new ImageButton(imageProvider.getConfigShadeButtonStyle());
        final TextButton settingsGameButton = new TextButton("config", homeScreenSkin);

        settingsGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //event.stop();
                //game.gotoSettingsScreen();
                System.out.println("Settings Button Pressed");
            }
        });
        table.add(settingsGameButton).fillX().uniformX();

        //table.add(headingLabel).width(VIEWPORT_WIDTH).height(HOMESTAGE_TOP_MENU_HEIGHT);
        table.row();
    }

    public ExitDialog getExitDialog(){
        return exitDialog;
    }

    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            app.getGameEvenListener().backButton(this);
        }
        return true;
    }
}
