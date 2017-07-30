package net.asg.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.asg.game.RodKastApplication;
import net.asg.game.menu.SettingsButton;
import net.asg.game.utils.GlobalConstants;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeStage extends RodkastStageAdapter {
    //TODO: callButton
    //TODO: shopButton
    //TODO: AD Resolver
    //TODO: Podcast Scroller
    //TODO: Social Medial
    //TODO: SettingButton

    protected Skin defaultSkin;
    protected Label.LabelStyle homeScreenLabelStyle;

    public HomeStage(RodKastApplication app){
        super(app);
        loadAssets();

        defaultSkin = imageProvider.getShadeUISkin();
        homeScreenLabelStyle = imageProvider.getDefaultLableStyle();

        //setUpStageTable();
        setUpSettings();
        setUpMenuItems();

        setInputProcessor();
    }

    private void setUpMenuItems() {
        //exitDialog = new ExitDialog("Do you really want to exit?", homeScreenSkin);
    }

    private void setUpStageTable(){
        Table mainScreenTable = new Table();
        mainScreenTable.debug();
        mainScreenTable.setBounds(0, 0, GlobalConstants.VIEWPORT_WIDTH, GlobalConstants.VIEWPORT_HEIGHT);
        mainScreenTable.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        mainScreenTable.setPosition(0,0);

        setUpTopRowButtons(mainScreenTable, homeScreenLabelStyle);
        setUpTopBodySection(mainScreenTable, homeScreenLabelStyle);
        setUpTopSocialSection(mainScreenTable, homeScreenLabelStyle);
        setUpTopAdSection(mainScreenTable, homeScreenLabelStyle);

        addActor(mainScreenTable);
    }

    private void setUpTopAdSection(Table table, LabelStyle defaultStyle) {
        Label adLabel = new Label("AD SECTION",defaultStyle);

        table.add(adLabel).height(GlobalConstants.HOMESTAGE_BOTTOM_MENU_HEIGHT);
    }

    private void setUpTopSocialSection(Table table, LabelStyle defaultStyle) {
        Label socialLabel = new Label("SOCIAL SECTION",defaultStyle);

        table.add(socialLabel).width(GlobalConstants.VIEWPORT_WIDTH).height((GlobalConstants.VIEWPORT_HEIGHT - getMenuOffSet()) * .3f).colspan(4);
        table.row();
    }

    private void setUpTopBodySection(Table table, LabelStyle defaultStyle) {
        Label bodyLabel = new Label("BODY SECTION",defaultStyle);

        table.add(bodyLabel).width(GlobalConstants.VIEWPORT_WIDTH).height((GlobalConstants.VIEWPORT_HEIGHT - getMenuOffSet()) * .7f).colspan(4);
        table.row();
    }

    private int getMenuOffSet(){
        return GlobalConstants.HOMESTAGE_TOP_MENU_HEIGHT + GlobalConstants.HOMESTAGE_BOTTOM_MENU_HEIGHT;
    }

    private void setUpSettings() {
        /*Rectangle settingsButtonSound = new Rectangle(getCamera().viewportWidth / 64,
                getCamera().viewportHeight * 13 / 20, getCamera().viewportHeight / 10,
                getCamera().viewportHeight / 10);*/
        Rectangle settingsButtonSound = new Rectangle(getCamera().viewportWidth / 8,
                getCamera().viewportHeight * 4/ 8, getCamera().viewportWidth / 8,
                getCamera().viewportHeight / 8);

        SettingsButton settingsButton = new SettingsButton(settingsButtonSound, defaultSkin, new SettingsButtonListener());
        settingsButton.debug();
        System.out.println("addActor(settingsButton)");

        addActor(settingsButton);
    }

    private void setUpTopRowButtons(Table table, LabelStyle defaultStyle) {
        setUpSettings();
        Label headingLabel = new Label("TOP SECTION",defaultStyle);

        //final ImageButton settingsGameButton = new ImageButton(imageProvider.getConfigShadeButtonStyle());
        //final TextButton settingsGameButton = new TextButton("config", homeScreenSkin);
        final ImageButton settingsButton = new ImageButton(imageProvider.getSettingsButtonStyle());
        final ImageButton callButton = new ImageButton(imageProvider.getCallButtonStyle());
        final ImageButton rodKastButton = new ImageButton(imageProvider.getRodKastButtonStyle());
        final ImageButton shopButton = new ImageButton(imageProvider.getShopButtonStyle());

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                System.out.println("Settings Button Pressed");

                //System.out.println(episodes.get((int) Math.floor(Math.random() * 40)));
                //event.handle();
                app.gotoPlayListScreen();
                //Gdx.app.exit();

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

    // Set up button listeners
    private class SettingsButtonListener implements SettingsButton.SettingsButtonListener{
        @Override
        public void onSettings() {
            System.out.println("Settings dfsfds Button Pressed");

            app.pushScreen(app.getCurrentScreen());
            app.gotoPlayListScreen();
        }
    }
}
