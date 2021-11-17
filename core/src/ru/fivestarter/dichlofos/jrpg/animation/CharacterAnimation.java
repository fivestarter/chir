package ru.fivestarter.dichlofos.jrpg.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.jrpg.model.Character;

public abstract class CharacterAnimation {
    protected Character character;
    protected float animationTime = 0f;
    protected final float unitScale;

    protected CharacterAnimation(Character character, float unitScale) {
        this.character = character;
        this.unitScale = unitScale;
    }

    abstract public void animate(Sprite sprite);
    abstract protected boolean isFinish();
    abstract protected void reset();

    public void idle() {

    }

    public void punch() {
        resetState();
        character.punch();
    }

    public void walk() {

    }

    private void resetState() {
        reset();
    }
}
