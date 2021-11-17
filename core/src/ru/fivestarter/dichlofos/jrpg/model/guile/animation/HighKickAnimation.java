package ru.fivestarter.dichlofos.jrpg.model.guile.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;

public class HighKickAnimation extends CharacterAnimation {
    private static final float FRAME_DURATION = 0.15f;

    private final Animation<TextureRegion> animation;

    private float highKickDuration = 0.7f;

    public HighKickAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, unitScale);
        this.animation = createHighKickAnimation(textureAtlas);
    }

    @Override
    public void animate(Sprite sprite) {
        highKickDuration -= Gdx.graphics.getDeltaTime();
        animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = animation.getKeyFrame(animationTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * unitScale, keyFrame.getRegionHeight() * unitScale);

        if (isFinish()) {
            idle();
        }
    }

    @Override
    protected boolean isFinish() {
        return highKickDuration <= 0;
    }

    @Override
    protected void reset() {
        highKickDuration = 0.7f;
    }

    private Animation<TextureRegion> createHighKickAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion highKickRegion = textureAtlas.findRegion("guileHighKick");
        TextureRegion[] punchFrames = new TextureRegion[5];
        punchFrames[0] = new TextureRegion(highKickRegion, 0, 0, 54, highKickRegion.getRegionHeight());
        punchFrames[1] = new TextureRegion(highKickRegion, 53, 0, 57, highKickRegion.getRegionHeight());
        punchFrames[2] = new TextureRegion(highKickRegion, 111, 0, 75, highKickRegion.getRegionHeight());
        punchFrames[3] = new TextureRegion(highKickRegion, 189, 0, 56, highKickRegion.getRegionHeight());
        punchFrames[4] = new TextureRegion(highKickRegion, 248, 0, highKickRegion.getRegionWidth() - 248, highKickRegion.getRegionHeight());

        return new Animation<>(FRAME_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
