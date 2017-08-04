package net.asg.game.providers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.Utils;

/**
 * Created by eboateng on 8/3/2017.
 */

public class MenuProvider implements Disposable {
    private static final String RIGHT_BUTTON = "right";
    private static final String LEFT_BUTTON = "left";
    private static final String FONT_TITLE = "font-title";
    private static final String LABEL_STYLE_DEFAULT = "default";

    private Skin skin;
    private BitmapFont defaultFont;

    public MenuProvider(Skin skin){
        if(skin == null){
            throw new NullPointerException("Skin cannot be null");
        }
        this.skin = skin;
        getTitleFont();
    }

    public Button getRightButton(){
        return new Button(skin, RIGHT_BUTTON);
    }

    public Button getBackButton(){
        return new Button(skin, LEFT_BUTTON);
    }

    private BitmapFont getTitleFont(){
        if(defaultFont == null){
            defaultFont = skin.getFont(FONT_TITLE);
        }
        return defaultFont;
    }

    public Label.LabelStyle getTitleLableStyle(){
        return skin.get(LABEL_STYLE_DEFAULT, Label.LabelStyle.class);
    }

    @Override
    public void dispose() {
        Utils.disposeObjects(skin,defaultFont);
    }
}
