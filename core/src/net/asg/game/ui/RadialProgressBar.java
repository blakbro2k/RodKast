package net.asg.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



/**
 * Allows to create circular cooldowns.
 *
 * @author serhiy
 */
public class RadialProgressBar extends Table{
    private RadialProgressBarStyle style;

    private static final float PREFERRED_RADIUS = 40;
    private static final float START_ANGLE = 90;

    private boolean isClockwise;
    private float value;
    private float min, max;

    private TextureRegionDrawable cooldownTexture;

    public RadialProgressBar(float min, float max, boolean clockwise, RadialProgressBarStyle style){
        setDebug(true, true);
        setStyle(style);

        this.isClockwise = clockwise;
        this.min = this.value = min;
        this.max = max;

        initialize();
    }

    public RadialProgressBar(float min, float max, boolean clockwise, Skin skin, String styleName) {
        this(min, max, clockwise, skin.get(styleName, RadialProgressBarStyle.class));
    }

    public RadialProgressBar(float min, float max, boolean clockwise, Skin skin){
        this(min, max, clockwise, skin, "default");
    }

    private void initialize(){
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle (RadialProgressBarStyle style) {
        if (style == null){
            throw new IllegalArgumentException("style cannot be null.");
        }
        if (style.timerColor == null){
            throw new IllegalArgumentException("timerColor in style not found.");
        }
        this.style = style;
        invalidateHierarchy();
    }

    /** Returns the progress bar's style. Modifying the returned style may not have an effect until
     * {@link #setStyle(RadialProgressBarStyle)} is called. */
    public RadialProgressBarStyle getStyle () {
        return style;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        validate();

        //draw background
        Color color = getColor();
        color.a *= parentAlpha;
        if (style.background != null) {
            setBackground(style.background);
        }

        //draw timer color
        float remainingPercentage = getPercent();

        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();

        Image cooldownTimer = new Image(cooldownTimer(remainingPercentage));
        cooldownTimer.setPosition(x, y);

        drawBackground(batch, parentAlpha, x, y);
        setColor(style.timerColor);
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
        float width = super.getWidth();
        width = width < PREFERRED_RADIUS ? PREFERRED_RADIUS : width;
        if (style.background != null){
            width = Math.max(width, style.background.getMinWidth());
        }
        return width;
    }

    @Override
    public float getPrefHeight(){
        float height = super.getHeight();
        height = height < PREFERRED_RADIUS ? PREFERRED_RADIUS : height;
        if (style.background != null){
            height = Math.max(height, style.background.getMinHeight());
        }
        return height;
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

            //draw background color circle
            if(style.backgroundColor != null){
                Color preserveColor = getColor();
                display.setColor(style.backgroundColor);

                display.fillCircle((int) getX(), (int) getY(), (int) getWidth()/2);
                setColor(preserveColor);
            }

            display.setColor(getColor());

            for (int count = 0; count < segments; count++) {
                float pcx = cx;
                float pcy = cy;
                float temp = cx;
                cx = cos * cx - sin * cy;
                cy = sin * temp + cos * cy;

                display.fillTriangle((int) getWidth()/2, (int) getHeight()/2,
                        (int) (getWidth()/2 + pcx), (int) (getHeight()/2 + pcy),
                        (int) (getWidth()/2 + cx), (int) (getHeight()/2 + cy));
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
        } else {
            return 360 * remainingPercentage - 360;
        }
    }

    private int calculateSegments(float angle) {
        return Math.max(1, (int) (6 * (float) Math.cbrt(Math.abs(angle)) * (Math.abs(angle) / 360.0f)));
    }

    static public class RadialProgressBarStyle {
        /** The progress bar download Button Optional. */
        public Drawable background;
        /** The progress bar background Optional. */
        public Color backgroundColor;
        public Color timerColor;

        public RadialProgressBarStyle () {
        }

        public RadialProgressBarStyle (Color backgroundColor, Color timerColor) {
            this.backgroundColor = backgroundColor;
            this.timerColor = timerColor;
        }

        public RadialProgressBarStyle (RadialProgressBar.RadialProgressBarStyle style) {
            this(style.backgroundColor, style.timerColor);
        }
    }
}