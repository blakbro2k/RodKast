package net.asg.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
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
    private static final float DEFAULT_BAR_SIZE = 6;

    private boolean isClockwise;
    private float value;
    private float min, max;

    private Table cooldownDisplay;
    private TextureRegionDrawable cooldownTexture;
    private Button downloadButton;

    public RadialProgressBar(float min, float max, boolean clockwise, RadialProgressBarStyle style){
        setDebug(true, true);
        setStyle(style);
        setSize(getPrefWidth(), getPrefHeight());

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
        //setTouchable(Touchable.enabled);

        cooldownDisplay = new Table();
        cooldownDisplay.setFillParent(true);
        cooldownDisplay.debugAll();

        addActor(cooldownDisplay);
    }

    public void setStyle (RadialProgressBarStyle style) {
        if (style == null){
            throw new IllegalArgumentException("style cannot be null.");
        }
        if (style.timerColor == null){
            throw new IllegalArgumentException("timerColor in style not found.");
        }
        this.style = style;

        //draw download button
        if(style.up != null){
            downloadButton = createDownloadButton();
        }
        //setBackground(style.background);
    }

    /** Returns the progress bar's style. Modifying the returned style may not have an effect until
     * {@link #setStyle(RadialProgressBarStyle)} is called. */
    public RadialProgressBarStyle getStyle () {
        return style;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        validate();
        cooldownDisplay.clear();

        //draw background
        Color color = getColor();
        color.a *= parentAlpha;
        if (style.background != null) {
            setBackground(style.background);
        }

        //draw background color circle
        if(style.backgroundColor != null){
            setColor(style.backgroundColor);
            //create a filled Circle to draw
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

        //draw download button;
        if(downloadButton != null){
            //draw download button
            downloadButton = createDownloadButton();
            downloadButton.setBounds(x + DEFAULT_BAR_SIZE/2, y + DEFAULT_BAR_SIZE/2,
                    width - DEFAULT_BAR_SIZE, height - DEFAULT_BAR_SIZE);
            downloadButton.draw(batch, parentAlpha);
        }
    }

    private Button createDownloadButton() {
        return new Button(style.up, style.down);
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
        width = width < 1 ? PREFERRED_RADIUS : width;
        if (style.background != null){
            width = Math.max(width, style.background.getMinWidth());
        }

        if (style.up != null){
            width = Math.max(width, style.up.getMinHeight());
        }

        if (style.down != null){
            width = Math.max(width, style.down.getMinHeight());
        }

        if (style.over != null){
            width = Math.max(width, style.over.getMinHeight());
        }
        return width;
    }

    @Override
    public float getPrefHeight(){
        float height = super.getHeight();
        height = height < 1 ? PREFERRED_RADIUS : height;
        if (style.background != null){
            height = Math.max(height, style.background.getMinHeight());
        }

        if (style.up != null){
            height = Math.max(height, style.up.getMinHeight());
        }

        if (style.down != null){
            height = Math.max(height, style.down.getMinHeight());
        }

        if (style.over != null){
            height = Math.max(height, style.over.getMinHeight());
        }
        return height;
    }

    public Button getDownloadButton(){
        return downloadButton;
    }

    public void setDownloadButton(Button button){
        downloadButton = button;
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
                display.fillTriangle((int) getWidth()/2, (int) getHeight()/2, (int) (getWidth()/2 + pcx), (int) (getHeight()/2 + pcy), (int) (getWidth()/2 + cx), (int) (getHeight()/2 + cy));
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
        public Drawable up, down, over;
        /** The progress bar download Button Optional. */
        public Drawable background;
        /** The progress bar background Optional. */
        public Color backgroundColor;
        public Color timerColor;

        public RadialProgressBarStyle () {
        }

        public RadialProgressBarStyle (Drawable up, Drawable down, Drawable over, Color backgroundColor, Color timerColor) {
            this.up = up;
            this.up = down;
            this.up = over;
            this.backgroundColor = backgroundColor;
            this.timerColor = timerColor;
        }

        public RadialProgressBarStyle (Drawable up, Color backgroundColor, Color timerColor) {
            this(up, null, null, backgroundColor, timerColor);
        }

        public RadialProgressBarStyle (Color backgroundColor, Color timerColor) {
            this(null, backgroundColor, timerColor);
        }

        public RadialProgressBarStyle (RadialProgressBar.RadialProgressBarStyle style) {
            this(style.up, style.down, style.over, style.backgroundColor, style.timerColor);
        }
    }
}