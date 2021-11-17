package ru.fivestarter.dichlofos.jrpg.model.guile.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;

public class PunchAnimation extends CharacterAnimation {
    private static final float FRAME_DURATION = 0.15f;
    private static final float PUNCH_DURATION = FRAME_DURATION * 2;

    public PunchAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, createAnimation(textureAtlas), unitScale);
    }

    @Override
    public void animate(Sprite sprite) {
        super.animate(sprite);

        if (isFinish()) {
            idle();
        }
    }

    @Override
    protected boolean isFinish() {
        return animationTime > PUNCH_DURATION;
    }

    private static Animation<TextureRegion> createAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion punchRegion = textureAtlas.findRegion("guilePunch");
        TextureRegion[] punchFrames = new TextureRegion[3];
        punchFrames[0] = new TextureRegion(punchRegion, 0, 0, 56, punchRegion.getRegionHeight());
        punchFrames[1] = new TextureRegion(punchRegion, 60, 0, 70, punchRegion.getRegionHeight());
        punchFrames[2] = new TextureRegion(punchRegion, 132, 0, punchRegion.getRegionWidth() - 132, punchRegion.getRegionHeight());

        return new Animation<>(FRAME_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
