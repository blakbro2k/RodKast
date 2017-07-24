package net.asg.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.ExitDialog;
import net.asg.game.menu.SettingsButton;
import net.asg.game.providers.ImageProvider;
import net.asg.game.providers.SoundProvider;
import net.asg.game.utils.Constants;
import net.asg.game.utils.Util;
import net.asg.game.utils.parser.XMLHandler;

import java.io.IOException;

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
    private XMLHandler handler;


    private ExitDialog exitDialog;
    private Skin homeScreenSkin;
    private Skin tempSkin;

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
        this.handler = app.getXMLHandler();

        imageProvider.pauseUntilLoaded();
        homeScreenSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();
        tempSkin = new Skin(imageProvider.getAtlas());

        try {
            handler.getTotalRssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Util.setUpCamera(camera);
        //setUpStageTable();
        setUpSettings();
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

        table.add(socialLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .3f).colspan(4);
        table.row();
    }

    private void setUpTopBodySection(Table table, LabelStyle defaultStyle) {
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);

        table.add(bodyLabel).width(VIEWPORT_WIDTH).height((VIEWPORT_HEIGHT - getMenuOffSet()) * .7f).colspan(4);
        table.row();
    }

    private int getMenuOffSet(){
        return HOMESTAGE_TOP_MENU_HEIGHT + HOMESTAGE_BOTTOM_MENU_HEIGHT;
    }

    private void setUpSettings() {
        /*Rectangle settingsButtonSound = new Rectangle(getCamera().viewportWidth / 64,
                getCamera().viewportHeight * 13 / 20, getCamera().viewportHeight / 10,
                getCamera().viewportHeight / 10);*/
        Rectangle settingsButtonSound = new Rectangle(getCamera().viewportWidth / 8,
                getCamera().viewportHeight * 4/ 8, getCamera().viewportWidth / 8,
                getCamera().viewportHeight / 8);

        SettingsButton settingsButton = new SettingsButton(settingsButtonSound, tempSkin, new SettingsButtonListener());
        settingsButton.debug();
        addActor(settingsButton);
    }

    private void setUpTopRowButtons(Table table, LabelStyle defaultStyle) {
        setUpSettings();
        Label headingLabel = new Label("TOP SECTION",defaultStyle);

        //final ImageButton settingsGameButton = new ImageButton(imageProvider.getConfigShadeButtonStyle());
        //final TextButton settingsGameButton = new TextButton("config", homeScreenSkin);
        final ImageButton settingsButton = new ImageButton(app.getImageProvider().getSettingsButtonStyle());
        final ImageButton callButton = new ImageButton(app.getImageProvider().getCallButtonStyle());
        final ImageButton rodKastButton = new ImageButton(app.getImageProvider().getRodKastButtonStyle());
        final ImageButton shopButton = new ImageButton(app.getImageProvider().getShopButtonStyle());

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //event.stop();
                //game.gotoSettingsScreen();
                //handler.getEpisode(0);
                System.out.println("Settings Button Pressed");
            }
        });

        callButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //event.stop();
                //game.gotoSettingsScreen();
                System.out.println("call Button Pressed");
            }
        });

        rodKastButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //event.stop();
                //game.gotoSettingsScreen();
                System.out.println("rodKast Button Pressed");
            }
        });

        shopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //event.stop();
                //game.gotoSettingsScreen();
                System.out.println("shop Button Pressed");
            }
        });

        table.add(callButton);
        table.add(shopButton);
        table.add(rodKastButton);
        table.add(settingsButton);

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

    // Set up button listeners
    private class SettingsButtonListener implements SettingsButton.SettingsButtonListener{

        @Override
        public void onShare() {
            System.out.println("Settings Button Pressed");
        }
    }
}
