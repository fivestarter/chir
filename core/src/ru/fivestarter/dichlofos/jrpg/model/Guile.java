package ru.fivestarter.dichlofos.jrpg.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.control.GuileController;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile implements Character {

    public static final float IDLE_DURATION = 0.25f;
    public static final float PUNCH_DURATION = 0.15f;
    private static final int UNIT_SCALE = 4;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> punchAnimation;

    private final Sprite sprite;
    private float idleTime = 0f;
    private float punchDuration = 0f;
    private float punchTime = 0f;

    private GuileController guileController;

    public Guile(TextureAtlas textureAtlas, int x, int y) {
        this.idleAnimation = createWalkAnimation(textureAtlas);
        this.punchAnimation = createPunchAnimation(textureAtlas);
        this.sprite = new Sprite();
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
            punchDuration = PUNCH_DURATION * 2; // не понятно почему так
        }
    }

    private boolean isPunching() {
        return punchDuration > 0;
    }

    private void animateIdle() {
        idleTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = idleAnimation.getKeyFrame(idleTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * UNIT_SCALE, keyFrame.getRegionHeight() * UNIT_SCALE);
    }

    private void animatePunch() {
        punchTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = punchAnimation.getKeyFrame(punchTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * UNIT_SCALE, keyFrame.getRegionHeight() * UNIT_SCALE);
    }

    private Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion("guileIdle");
        TextureRegion[] walkFrames = Arrays.stream(idleRegion.split(idleRegion.getRegionWidth() / 3, idleRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(IDLE_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP_PINGPONG);
    }

    private Animation<TextureRegion> createPunchAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion punchRegion = textureAtlas.findRegion("guilePunch");
        TextureRegion[] punchFrames = new TextureRegion[3];
        punchFrames[0] = new TextureRegion(punchRegion, 0, 0, 56, punchRegion.getRegionHeight());
        punchFrames[1] = new TextureRegion(punchRegion, 60, 0, 70, punchRegion.getRegionHeight());
        punchFrames[2] = new TextureRegion(punchRegion, 132, 0, punchRegion.getRegionWidth() - 132, punchRegion.getRegionHeight());

        return new Animation<>(PUNCH_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
