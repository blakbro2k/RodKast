package net.asg.game.Actors;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

/**
 * Created by Blakbro2k on 8/5/2017.
 */

public class MusicPlayerActor  extends Actor {
    private Music music;
    private Skin skin;
    private float songDuration;
    private float currentPosition;

    private Slider slider;
    private boolean sliderUpdating = false;

    public MusicPlayerActor(Skin skin){
        this.skin = skin;
    }

    public void loadMusic(){

    }

    public void play(){

    }

    public void pause(){

    }

    public void stop(){

    }

    public void reset(){

    }

    public void setSliderPosition(){

    }

    public void getSliderPosition(){

    }

    public float getSoundDuriation(){
        return songDuration;
    }
}
