package net.asg.game.menu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Blakbro2k on 8/2/2017.
 */

public class DownloadButton extends ButtonAdapter {
    public interface DownloadButtonListener {
        public void onDownload();
    }

    private DownloadButtonListener listener;

    public DownloadButton(Rectangle bounds, Skin skin, DownloadButtonListener listener) {
        super(bounds, skin);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return ButtonAdapter.DOWNLOAD_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onDownload();
    }
}


