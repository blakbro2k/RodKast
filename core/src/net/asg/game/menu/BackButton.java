package net.asg.game.menu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by eboateng on 7/31/2017.
 */

public class BackButton extends ButtonAdapter {

    public interface BackButtonListener {
        public void onBackPress();
    }

    private BackButton.BackButtonListener listener;

    public BackButton(Rectangle bounds, Skin skin, BackButton.BackButtonListener listener) {
        super(bounds, skin);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return ButtonAdapter.BACK_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onBackPress();
    }
}
