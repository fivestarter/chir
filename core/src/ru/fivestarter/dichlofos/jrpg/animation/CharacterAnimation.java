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
    abstract protected void reset();

    public void idle() {
        if (isFinish()) {
            resetState();
            animationState.setIdleState();
        }
    }

    public void highKick() {
        if (isFinish()) {
            resetState();
            animationState.setHighKickState();
        }
    }

    public void punch() {
        if (isFinish()) {
            resetState();
            animationState.setPunchState();
        }
    }


    private void resetState() {
        animationTime = 0f;
        reset();
    }
}
