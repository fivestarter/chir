package ru.fivestarter.dichlofos.game.gta.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class HeroAnimation extends Animation<TextureRegion> {

    private final TextureRegion calmFrame;

    public HeroAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames, TextureRegion calmFrame, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
        this.calmFrame = calmFrame;
    }

    public TextureRegion getCalmFrame() {
        return calmFrame;
    }
}
