package ru.fivestarter.dichlofos.jrpg.model.guile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;
import ru.fivestarter.dichlofos.jrpg.control.GuileController;
import ru.fivestarter.dichlofos.jrpg.model.Character;
import ru.fivestarter.dichlofos.jrpg.model.guile.animation.HighKickAnimation;
import ru.fivestarter.dichlofos.jrpg.model.guile.animation.IdleAnimation;
import ru.fivestarter.dichlofos.jrpg.model.guile.animation.PunchAnimation;

public class Guile implements Character, AnimationState {


    private static final float UNIT_SCALE = 3.5f;

    private CharacterAnimation currentAnimation;
    private final IdleAnimation idleAnimation;
    private final PunchAnimation punchAnimation;
    private final HighKickAnimation highKickAnimation;

    private final Sprite sprite;
    private GuileController guileController;

    public Guile(TextureAtlas textureAtlas, int x, int y) {
        this.idleAnimation = new IdleAnimation(this, textureAtlas, UNIT_SCALE);
        this.punchAnimation = new PunchAnimation(this, textureAtlas, UNIT_SCALE);
        this.highKickAnimation = new HighKickAnimation(this, textureAtlas, UNIT_SCALE);
        currentAnimation = idleAnimation;
        this.sprite = new Sprite();
        this.sprite.setPosition(x, y);
        this.guileController = new GuileController(this);
    }

    public void draw(SpriteBatch batch) {
        guileController.handle();
        currentAnimation.animate(sprite);
        sprite.draw(batch);
    }

    @Override
    public void punch() {
        currentAnimation.punch();
    }

    @Override
    public void highKick() {
        currentAnimation.highKick();
    }

    @Override
    public void move() {

    }




    @Override
    public void setPunchState() {
        currentAnimation = punchAnimation;
    }

    @Override
    public void setHighKickState() {
        currentAnimation = highKickAnimation;
    }

    @Override
    public void setIdleState() {
        currentAnimation = idleAnimation;
    }
}
