package net.asg.game.menu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by eboateng on 7/12/2017.
 */

public class SettingsButton extends ButtonAdapter {

    public interface SettingsButtonListener {
        public void onSettings();
    }

    private SettingsButtonListener listener;

    public SettingsButton(Rectangle bounds, Skin skin, SettingsButtonListener listener) {
        super(bounds, skin);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return ButtonAdapter.SETTINGS_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onSettings();
    }
}
