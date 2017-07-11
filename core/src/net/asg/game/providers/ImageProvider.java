package net.asg.game.providers;

import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.Constants;
import net.asg.game.utils.Util;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class ImageProvider implements Disposable{
    private AssetsManager assetsManager;
    private Skin defaultSkin;
    private Skin shadeSkin;
    private TextureAtlas atlas;


    public ImageProvider(AssetsManager assetsManager) {
        this.assetsManager = assetsManager;
        setUpImageLoaders();
    }

    private void update(){
        assetsManager.getManager().update();
    }

    public void setUpImageLoaders(){
        SkinParameter skinParams = new SkinParameter(Constants.DEFAULT_UISKIN_ATLAS);
        SkinParameter shadeSkinParams = new SkinParameter(Constants.SHADE_UISKIN_ATLAS);

        assetsManager.getManager().load(Constants.BAD_LOGIC_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_IMAGE_PATH, Texture.class);
        assetsManager.getManager().load(Constants.DEFAULT_FONT_PATH, BitmapFont.class);
        assetsManager.getManager().load(Constants.DEFAULT_ATLAS_PATH, TextureAtlas.class);
        assetsManager.getManager().load(Constants.DEFAULT_UISKIN_JSON, Skin.class, skinParams);
        assetsManager.getManager().load(Constants.SHADE_UISKIN_JSON, Skin.class, shadeSkinParams);

        //getDefaultFont = new BitmapFont(Gdx.files.internal("data/default.fnt"), Gdx.files.internal("data/default.png"), false);
    }

    public void pauseUntilLoadedImages(){
        assetsManager.getManager().finishLoading();
    }

    public BitmapFont getDefaultFont() {
        if(assetsManager.getManager().isLoaded(Constants.DEFAULT_FONT_PATH)) {
            return assetsManager.getManager().get(Constants.DEFAULT_FONT_PATH);
        }
        return null;
    }

    public Texture getBadLogicImage() {
        if(assetsManager.getManager().isLoaded(Constants.BAD_LOGIC_IMAGE_PATH)) {
            return assetsManager.getManager().get(Constants.BAD_LOGIC_IMAGE_PATH);
        }
        return null;
    }

    public TextureAtlas getAtlas(){
        if(assetsManager.getManager().isLoaded(Constants.DEFAULT_ATLAS_PATH)) {
            atlas = assetsManager.getManager().get(Constants.DEFAULT_ATLAS_PATH);
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

    public Skin getDefaultUISkin() {
        if(assetsManager.getManager().isLoaded(Constants.DEFAULT_UISKIN_JSON)) {
            defaultSkin = assetsManager.getManager().get(Constants.DEFAULT_UISKIN_JSON);
        }
        return defaultSkin;
    }

    public Skin getShadeUISkin() {
        if(assetsManager.getManager().isLoaded(Constants.SHADE_UISKIN_JSON)) {
            shadeSkin = assetsManager.getManager().get(Constants.SHADE_UISKIN_JSON);
        }
        return shadeSkin;
    }

    @Override
    public void dispose(){
        Util.disposeObjects(assetsManager,defaultSkin,atlas,shadeSkin);
    }

    public ImageButtonStyle getConfigButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        Skin tempSkin = getDefaultUISkin();

        tempButtonStyle.up = tempSkin.getDrawable("default-round");  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable("default-round-down");  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable("default-select");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }

    public ImageButtonStyle getConfigShadeButtonStyle() {
        ImageButtonStyle tempButtonStyle = new ImageButtonStyle();  //Instantiate
        Skin tempSkin = getShadeUISkin();

        tempButtonStyle.up = tempSkin.getDrawable("button");  //Set image for not pressed button
        tempButtonStyle.down = tempSkin.getDrawable("button-down");  //Set image for pressed
        tempButtonStyle.over = tempSkin.getDrawable("button");  //set image for mouse over
        tempButtonStyle.pressedOffsetX = 1;
        tempButtonStyle.pressedOffsetY = -1;
        return tempButtonStyle;
    }
}
