package net.asg.game.menu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by eboateng on 7/13/2017.
 */

public class CallButton extends ButtonAdapter {

    public interface CallButtonListener {
        public void onCall();
    }

    private CallButton.CallButtonListener listener;

    public CallButton(Rectangle bounds, Skin skin, CallButton.CallButtonListener listener) {
        super(bounds, skin);
        this.listener = listener;
    }

    public CallButton(Rectangle bounds, Skin skin) {
        super(bounds, skin);
    }

    @Override
    protected String getRegionName() {
        return ButtonAdapter.CALL_REGION_NAME;
    }

    @Override
    public void touched() {

    }
}
