package net.asg.game.providers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.GlobalConstants;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class ImageProvider implements Disposable{
    public final String SHOP_UP_ATLAS = "shop";
    public final String SHOP_DOWN_ATLAS = "shop";
    public final String SHOP_OVER_ATLAS = "shop";
    public final String SETTINGS_UP_ATLAS = "settings";
    public final String SETTINGS_DOWN_ATLAS = "settings";
    public final String SETTINGS_OVER_ATLAS = "settings";
    public final String CALL_UP_ATLAS = "call";
    public final String CALL_DOWN_ATLAS = "call";
    public final String CALL_OVER_ATLAS = "call";
    public final String RODKAST_UP_ATLAS = "RodKast_small";
    public final String RODKAST_DOWN_ATLAS = "RodKast_small";
    public final String RODKAST_IMAGE = "rodkast_icon";
    public final String RODKAST_OVER_ATLAS = "RodKast_small";

    private AssetsManager assetsManager;
    private TextureAtlas atlas;

    ImageProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
    }

    void setUpImageLoaders(){
        assetsManager.getManager().load(GlobalConstants.BAD_LOGIC_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(GlobalConstants.DEFAULT_FONT_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(GlobalConstants.DEFAULT_FONT_PATH, BitmapFont.class);
        assetsManager.getManager().load(GlobalConstants.DEFAULT_ATLAS_PATH, TextureAtlas.class);
    }

    public BitmapFont getDefaultFont() {
        if(assetsManager.getManager().isLoaded(GlobalConstants.DEFAULT_FONT_PATH)) {
            return assetsManager.getManager().get(GlobalConstants.DEFAULT_FONT_PATH);
        }
        return null;
    }

    public Texture getBadLogicImage() {
        if(assetsManager.getManager().isLoaded(GlobalConstants.BAD_LOGIC_IMAGE_PATH)) {
            return assetsManager.getManager().get(GlobalConstants.BAD_LOGIC_IMAGE_PATH);
        }
        return null;
    }

    public TextureAtlas getAtlas(){
        if(assetsManager.getManager().isLoaded(GlobalConstants.DEFAULT_ATLAS_PATH)) {
            atlas = assetsManager.getManager().get(GlobalConstants.DEFAULT_ATLAS_PATH);
        }
        return atlas;
    }

    public Label.LabelStyle getDefaultLableStyle(){
        BitmapFont font = getDefaultFont();
        if(font != null){
            return new Label.LabelStyle(font, Color.WHITE);
        }
        return null;
    }

    @Override
    public void dispose(){
        Utils.disposeObjects(atlas);
    }

    public ImageButtonStyle getSettingsButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        //Skin tempSkin = getDefaultUISkin();
        TextureAtlas temp = getAtlas();

        Skin tempSkin = new Skin(temp);
        //empSkin.get
        //temp.findRegion()

        tempButtonStyle.up = tempSkin.getDrawable(SETTINGS_UP_ATLAS);  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable(SETTINGS_DOWN_ATLAS);  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable(SETTINGS_OVER_ATLAS);  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getCallButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        TextureAtlas temp = getAtlas();

        Skin tempSkin = new Skin(temp);

        tempButtonStyle.up = tempSkin.getDrawable(CALL_UP_ATLAS);  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable(CALL_DOWN_ATLAS);  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable(CALL_OVER_ATLAS);  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getShopButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        //Skin tempSkin = getDefaultUISkin();

        TextureAtlas temp = getAtlas();
        Skin tempSkin = new Skin(temp);

        //temp.findRegion()

        tempButtonStyle.up = tempSkin.getDrawable(SHOP_UP_ATLAS);  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable(SHOP_DOWN_ATLAS);  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable(SHOP_OVER_ATLAS);  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getRodKastButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        //Skin tempSkin = getDefaultUISkin();

        TextureAtlas temp = getAtlas();
        Skin tempSkin = new Skin(temp);

        //temp.findRegion()

        tempButtonStyle.up = tempSkin.getDrawable(RODKAST_UP_ATLAS);  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable(RODKAST_DOWN_ATLAS);  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable(RODKAST_OVER_ATLAS);  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public TextureRegion getRodKastImage(){
        return getAtlas().findRegion(RODKAST_IMAGE);
    }
}
