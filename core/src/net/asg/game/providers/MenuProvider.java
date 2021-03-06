package net.asg.game.providers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import net.asg.game.utils.MessageCatalog;
import net.asg.game.utils.Utils;

/**
 * Created by eboateng on 8/3/2017.
 */

public class MenuProvider implements Disposable {
    public static final String PLAY_BUTTON = "right";
    public static final String LEFT_BUTTON = "left";
    public static final String FONT_TITLE = "font-title";
    public static final String LABEL_STYLE_DEFAULT = "default";
    public static final String LABEL_STYLE_OPTIONAL = "optional";
    public static final String LABEL_STYLE_ERROR = "error";
    public static final String LABEL_STYLE_TITLE_PLAIN = "title-plain";
    public static final String LABEL_STYLE_SUBTITLE = "subtitle";
    public static final String LABEL_STYLE_TITLE = "title";
    public static final String SEEKER_BAR_STYLE = "default-horizontal";

    private Skin skin;
    private BitmapFont defaultFont;

    public MenuProvider(Skin skin){
        if(skin == null){
            throw new NullPointerException(MessageCatalog.NULL_SKIN_OBJECT_MSG);
        }
        this.skin = skin;
        getTitleFont();
    }

    //Buttons
    public Button getRightButton(){
        return new Button(skin, PLAY_BUTTON);
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

    //Label Style
    public Label.LabelStyle getDefaultLabelStyle(){
        return skin.get(LABEL_STYLE_DEFAULT, Label.LabelStyle.class);
    }

    public Label.LabelStyle getOptionalLableStyle(){
        return skin.get(LABEL_STYLE_OPTIONAL, Label.LabelStyle.class);
    }

    public Label.LabelStyle getErrorLableStyle(){
        return skin.get(LABEL_STYLE_ERROR, Label.LabelStyle.class);
    }

    public Label.LabelStyle getSubTitleLableStyle(){
        return skin.get(LABEL_STYLE_SUBTITLE, Label.LabelStyle.class);
    }

    public Label.LabelStyle getTitleLableStyle(){
        return skin.get(LABEL_STYLE_TITLE, Label.LabelStyle.class);
    }

    public Label.LabelStyle getTitlePlainLableStyle(){
        return skin.get(LABEL_STYLE_TITLE_PLAIN, Label.LabelStyle.class);
    }

    @Override
    public void dispose() {
        Utils.disposeObjects(skin,defaultFont);
    }
}
