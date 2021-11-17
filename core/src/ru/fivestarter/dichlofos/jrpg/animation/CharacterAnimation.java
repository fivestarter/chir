package ru.fivestarter.dichlofos.jrpg.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class CharacterAnimation {
    protected AnimationState animationState;
    protected float animationTime = 0f;
    protected final float unitScale;

    protected CharacterAnimation(AnimationState animationState, float unitScale) {
        this.animationState = animationState;
        this.unitScale = unitScale;
    }

    abstract public void animate(Sprite sprite);
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
