package ru.fivestarter.dichlofos.jrpg.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PunchAnimation extends CharacterAnimation {
    private static final float FRAME_DURATION = 0.15f;

    private final Animation<TextureRegion> animation;

    private float punchDuration = FRAME_DURATION * 2;

    public PunchAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, unitScale);
        this.animation = createPunchAnimation(textureAtlas);
    }

    @Override
    public void animate(Sprite sprite) {
        punchDuration -= Gdx.graphics.getDeltaTime();
        animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = animation.getKeyFrame(animationTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * unitScale, keyFrame.getRegionHeight() * unitScale);
        idle();
    }

    @Override
    protected boolean isFinish() {
        return punchDuration <= 0;
    }

    @Override
    protected void reset() {
        punchDuration = FRAME_DURATION * 2;
    }

    private Animation<TextureRegion> createPunchAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion punchRegion = textureAtlas.findRegion("guilePunch");
        TextureRegion[] punchFrames = new TextureRegion[3];
        punchFrames[0] = new TextureRegion(punchRegion, 0, 0, 56, punchRegion.getRegionHeight());
        punchFrames[1] = new TextureRegion(punchRegion, 60, 0, 70, punchRegion.getRegionHeight());
        punchFrames[2] = new TextureRegion(punchRegion, 132, 0, punchRegion.getRegionWidth() - 132, punchRegion.getRegionHeight());

        return new Animation<>(FRAME_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
