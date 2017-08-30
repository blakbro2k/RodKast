package net.asg.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



/**
 * Allows to create circular cooldowns.
 *
 * @author serhiy
 */
public class RadialProgressBar extends Button {
    private static final float PREFERRED_RADIUS = 100;
    private static final float START_ANGLE = 90;

    private final boolean isClockwise;
    private float value;
    private float min, max;

    private Table cooldownDisplay;
    private TextureRegionDrawable cooldownTexture;

    public RadialProgressBar(float min, float max, boolean clockwise){
        this.isClockwise = clockwise;
        this.min = min;
        this.max = max;
        initialize();
    }

    public RadialProgressBar(boolean clockwise){
        this(0f, 1f, clockwise);
        //initialize();
    }

    private void initialize(){
        cooldownDisplay = new Table();
        //cooldownDisplay.setPosition(0, 0);
        cooldownDisplay.setSize(getPrefWidth(), getPrefHeight());

        addActor(cooldownDisplay);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        cooldownDisplay.clear();
        float remainingPercentage = getPercent();
        Color color = getColor();

        Image cooldownTimer = new Image(cooldownTimer(remainingPercentage));
        cooldownTimer.setColor(color.r , color.g, color.b, parentAlpha);
        cooldownTimer.draw(batch, parentAlpha);
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        //value = clamp(Math.round(value / stepSize) * stepSize);
        this.value = value;
    }

    protected float clamp(float value){
        return MathUtils.clamp(value, min, max);
    }

    public float getPercent(){
        return (value - min) / (max - min);
    }

    @Override
    public float getPrefWidth(){
        return PREFERRED_RADIUS;
    }

    @Override
    public float getPrefHeight(){
        return PREFERRED_RADIUS;
    }

    private Drawable cooldownTimer(float remainingPercentage) {
        if (remainingPercentage > 1.0f || remainingPercentage < 0.0f) {
            return null;
        }

        float radius = Math.min(getWidth()/2, getHeight()/2);
        float angle = calculateAngle(remainingPercentage);
        int segments = calculateSegments(angle);

        Pixmap display = new Pixmap((int) getWidth(), (int) getHeight(), Format.RGBA8888);

        Blending blending = display.getBlending();

        try {
            float theta = (2 * MathUtils.PI * (angle / 360.0f)) / segments;
            float cos = MathUtils.cos(theta);
            float sin = MathUtils.sin(theta);
            float cx = radius * MathUtils.cos(START_ANGLE * MathUtils.degreesToRadians);
            float cy = radius * MathUtils.sin((-1 * START_ANGLE) * MathUtils.degreesToRadians);

            display.setColor(getColor());

            for (int count = 0; count < segments; count++) {
                float pcx = cx;
                float pcy = cy;
                float temp = cx;
                cx = cos * cx - sin * cy;
                cy = sin * temp + cos * cy;
                display.fillTriangle((int) getWidth()/2,
                        (int) getHeight()/2,
                        (int) (getWidth()/2 + pcx),
                        (int) (getHeight()/2 + pcy),
                        (int) (getWidth()/2 + cx),
                        (int) (getHeight()/2 + cy));
            }

            display.setBlending(Blending.None);

            if (cooldownTexture == null) {
                cooldownTexture = new TextureRegionDrawable(new TextureRegion(new Texture(display)));
            } else {
                cooldownTexture.getRegion().getTexture().draw(display, 0, 0);
            }

            return cooldownTexture;
        } finally {
            display.setBlending(blending);

            display.dispose();
        }
    }

    private float calculateAngle(float remainingPercentage) {
        if (isClockwise) {
            return 360 * remainingPercentage;
            //return 360 * remainingPercentage - 360;
        } else {
            //return 360 - 360 * remainingPercentage;
            return 360 * remainingPercentage;
        }
    }

    private int calculateSegments(float angle) {
        return Math.max(1, (int) (6 * (float) Math.cbrt(Math.abs(angle)) * (Math.abs(angle) / 360.0f)));
    }
}