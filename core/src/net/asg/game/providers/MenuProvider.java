package net.asg.game.providers;

import com.badlogic.gdx.graphics.Color;
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
    private static final String FONT_TITLE = "font-title";

    private Skin skin;

    public MenuProvider(Skin skin){
        if(skin == null){
            throw new NullPointerException("Skin cannot be null");
        }
        this.skin = skin;
    }

    public Button getRightButton(){
        return new Button(skin, RIGHT_BUTTON);
    }

    private BitmapFont getTitleFont(){
        return skin.getFont(FONT_TITLE);
    }

    public Label.LabelStyle getTitleLableStyle(){
        BitmapFont font = getTitleFont();
        if(font != null){
            return new Label.LabelStyle(font, Color.WHITE);
        }
        return null;
    }

    @Override
    public void dispose() {
        Utils.disposeObjects(skin);
    }
}
