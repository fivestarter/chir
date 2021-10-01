package ru.fivestarter.dichlofos.jrpg.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.control.GuileController;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile implements Character {

    public static final float FRAME_DURATION = 0.25f;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> punchAnimation;

    private final Sprite sprite;
    private float idleTime = 0f;
    private float punchDuration = 0f;
    private float punchTime = 0f;
    private GuileController guileController;

    public Guile(TextureAtlas textureAtlas, int x, int y, int with, int height) {
        this.idleAnimation = createWalkAnimation(textureAtlas);
        this.punchAnimation = createPunchAnimation(textureAtlas);
        this.sprite = new Sprite(idleAnimation.getKeyFrame(idleTime, true), x, y, with, height);
        this.sprite.setSize(with, height);
        this.sprite.setPosition(x, y);
        this.guileController = new GuileController(this);
    }

    public void draw(SpriteBatch batch) {
        guileController.handle();

        if (isPunching()) {
            punchDuration -= Gdx.graphics.getDeltaTime();
            animatePunch();
        } else {
            animateIdle();
        }

        sprite.draw(batch);
    }

    @Override
    public void punch() {
        if (!isPunching()) {
            punchTime = 0;
            punchDuration = FRAME_DURATION * 2; // не понятно почему так
        }
    }

    private boolean isPunching() {
        return punchDuration > 0;
    }

    private void animateIdle() {
        idleTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(idleAnimation.getKeyFrame(idleTime));
    }

    private void animatePunch() {
        punchTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(punchAnimation.getKeyFrame(punchTime));
    }

    private Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion("guileIdle");
        TextureRegion[] walkFrames = Arrays.stream(idleRegion.split(idleRegion.getRegionWidth() / 3, idleRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(FRAME_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP_PINGPONG);
    }

    private Animation<TextureRegion> createPunchAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion punchRegion = textureAtlas.findRegion("guilePunch");
        TextureRegion[] punchFrames = Arrays.stream(punchRegion.split(punchRegion.getRegionWidth() / 3, punchRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(FRAME_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
