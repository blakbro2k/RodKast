package net.asg.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by eboateng on 8/23/2017.
 */

public class RadialProgressBar extends Widget implements Disableable {
    private Image image;
    private RadialProgressBarStyle style;
    private float min, max, stepSize;

    public RadialProgressBar(float min, float max, float stepSize, Skin skin){
        this(min, max, stepSize, skin.get("default", RadialProgressBarStyle.class));
        //setSkin(skin);
    }

    public RadialProgressBar(float min, float max, float stepSize, Skin skin, String styleName){
        this(min, max, stepSize, skin.get(styleName, RadialProgressBarStyle.class));
        //setSkin(skin);
    }

    public RadialProgressBar(float min, float max, float stepSize, RadialProgressBarStyle style){
        //super(style);
        if (min > max){
            throw new IllegalArgumentException("max must be > min. min, max: " + min + ", " + max);
        }
        if (stepSize < 1) {
            throw new IllegalArgumentException("stepSize must be > 0: " + stepSize);
        }
        setStyle(style);

        image = new Image();
        image.setScaling(Scaling.fit);
        this.min = min;
        this.max = max;
        this.stepSize = stepSize;

        //add(image);
        //setStyle(style);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle(RadialProgressBarStyle style){
        if (style == null){
            throw new IllegalArgumentException("style must be a RadialProgressBarStyle");
        }
        this.setStyle(style);
    }

    public RadialProgressBarStyle getStyle() {
        return style;
    }

    protected void updateImage (){
        Drawable drawable = null;
        //if (isDisabled() && style.imageDisabled != null){
        //    drawable = style.imageDisabled;

        //}

        image.setDrawable(drawable);
    }

    public void draw(Batch batch, float parentAlpha){
        updateImage();
        //super.draw();
    }

    public Image getImage(){
        return image;
    }

    @Override
    public void setDisabled(boolean isDisabled) {

    }

    @Override
    public boolean isDisabled() {
        return false;
    }


    static public class RadialProgressBarStyle {
        public Drawable imageDownload;

        public RadialProgressBarStyle() {
        }

        public RadialProgressBarStyle(Drawable imageDownload) {
            this.imageDownload = imageDownload;
        }

        public RadialProgressBarStyle(RadialProgressBarStyle style) {
            this.imageDownload = style.imageDownload;
        }
    }
}
