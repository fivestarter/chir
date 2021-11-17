package ru.fivestarter.dichlofos.jrpg.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class CharacterAnimation {
    protected final AnimationState animationState;
    protected final Animation<TextureRegion> animation;
    protected float animationTime = 0f;
    protected final float unitScale;

    protected CharacterAnimation(AnimationState animationState, Animation<TextureRegion> animation, float unitScale) {
        this.animationState = animationState;
        this.animation = animation;
        this.unitScale = unitScale;
    }

    public void animate(Sprite sprite) {
        animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = animation.getKeyFrame(animationTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * unitScale, keyFrame.getRegionHeight() * unitScale);
    }

    abstract protected boolean isFinish();

    public void idle() {
        if (isFinish()) {
            reset();
            animationState.setIdleState();
        }
    }

    public void highKick() {
        if (isFinish()) {
            reset();
            animationState.setHighKickState();
        }
    }

    public void punch() {
        if (isFinish()) {
            reset();
            animationState.setPunchState();
        }
    }

    protected void reset() {
        animationTime = 0f;
    }
}
