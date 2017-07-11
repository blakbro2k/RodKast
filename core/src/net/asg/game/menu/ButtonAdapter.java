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

    protected final Rectangle bounds;
    private final Skin skin;


    public ButtonAdapter(Rectangle bounds, Skin skin) {
        this.bounds = bounds;
        this.skin = skin;

        setWidth(bounds.width);
        setHeight(bounds.height);
        setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        //skin = new Skin();
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
