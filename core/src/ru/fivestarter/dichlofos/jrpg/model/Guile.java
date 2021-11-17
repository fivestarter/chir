package ru.fivestarter.dichlofos.jrpg.model;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.animation.*;
import ru.fivestarter.dichlofos.jrpg.control.GuileController;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile implements Character, AnimationState {

    public static final float WALK_DURATION = 0.25f;
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


    private Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion walkRegion = textureAtlas.findRegion("guileWalk");
        TextureRegion[] walkFrames = Arrays.stream(walkRegion.split(walkRegion.getRegionWidth() / 5, walkRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(WALK_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP);
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
