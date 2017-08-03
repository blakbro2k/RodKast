package net.asg.game.menu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public abstract class ButtonAdapter extends Button {
    public static final String SHOP_REGION_NAME = "shop";
    public static final String SETTINGS_REGION_NAME = "music";
    public static final String CALL_REGION_NAME = "call";
    public static final String RODKAST_REGION_NAME = "rodkast_icon";
    public static final String DOWNLOAD_REGION_NAME = "sound";
    public static final String BACK_REGION_NAME = "music";

    protected final Rectangle bounds;
    private final Skin skin;


    public ButtonAdapter(Rectangle bounds, Skin skin) {
        super(skin);
        this.bounds = bounds;
        this.skin = skin;

        setWidth(bounds.width);
        setHeight(bounds.height);
        setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        //skin.addRegions(AssetsManager.getTextureAtlas());

        loadTextureRegion();
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched();
                loadTextureRegion();
                return true;
            }
        });
    }

    protected void loadTextureRegion() {
        ButtonStyle style = new ButtonStyle();
        style.up = skin.getDrawable(getRegionName());
        setStyle(style);
    }

    protected abstract String getRegionName();

    public abstract void touched();

    public Rectangle getBounds() {
        return bounds;
    }
}
