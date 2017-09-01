package net.asg.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

/**
 * Created by Blakbro2k on 8/31/2017.
 */

public class RadialDownloadButtonGroup extends WidgetGroup {
    public RadialDownloadButtonGroup(Actor... actors){
        super(actors);
    }

    public void setValue(float value){
        ((RadialProgressBar) findActor(PlayListWidget.PROGRESS_ACTOR_NAME)).setValue(value);
    }

    public boolean addListener(EventListener listener){
        return findActor(PlayListWidget.DOWNLOAD_ACTOR_NAME).addListener(listener);
    }
}
