package net.asg.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created by Blakbro2k on 8/31/2017.
 */

public class RadialDownloadButtonGroup extends WidgetGroup {
    public RadialDownloadButtonGroup(Actor... actors){
        super(actors);

        initialize(actors);
    }

    private void initialize(Actor... actors) {
        if(actors == null) {
            throw new GdxRuntimeException("input actors cannot be null");
        }

        if(actors.length < 0 || actors.length > 2){
            throw new GdxRuntimeException("RadialDownloadButtonGroup input actors must be only 2: RadialProgressBar, Button");
        }

        //setFillParent(true);
        validate();
        System.out.println("width: " + getParent());

        Actor bar = actors[0];
        Actor button = actors[1];

        if(bar != null) {
            float localWidth;
            float localHeight;

            localWidth = bar.getWidth();
            localHeight = bar.getHeight();

            button.setPosition(localWidth / 2f - button.getWidth() / 2f, localHeight / 2f - button.getHeight() / 2f);
        }
    }

    public void setValue(float value){
        ((RadialProgressBar) findActor(PlayListWidget.PROGRESS_ACTOR_NAME)).setValue(value);
    }

    public boolean addListener(EventListener listener){
        return findActor(PlayListWidget.DOWNLOAD_ACTOR_NAME).addListener(listener);
    }
}
