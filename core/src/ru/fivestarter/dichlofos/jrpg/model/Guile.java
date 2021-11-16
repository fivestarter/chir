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
    public static final float HIGH_KICK_DURATION = 0.35f;
    private static final float UNIT_SCALE = 3.5f;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> punchAnimation;
    private final Animation<TextureRegion> highKickAnimation;

    private final Sprite sprite;
    private float idleTime = 0f;
    private float punchDuration = 0f;
    private float punchTime = 0f;
    private float highKickDuration = 0f;
    private float highKickTime = 0f;

    private GuileController guileController;

    public Guile(TextureAtlas textureAtlas, int x, int y) {
        this.idleAnimation = createWalkAnimation(textureAtlas);
        this.punchAnimation = createPunchAnimation(textureAtlas);
        this.highKickAnimation = createHighKickAnimation(textureAtlas);
        this.sprite = new Sprite();
        this.sprite.setPosition(x, y);
        this.guileController = new GuileController(this);
    }

    public void draw(SpriteBatch batch) {
        guileController.handle();

        if (isPunch()) {
            punchDuration -= Gdx.graphics.getDeltaTime();
            animatePunch();
        } else if (isHighKick()){
            highKickDuration -= Gdx.graphics.getDeltaTime();
            animateHighKick();
        } else {
            animateIdle();
        }

        sprite.draw(batch);
    }

    @Override
    public void punch() {
        if (isIdle()) {
            punchTime = 0;
            punchDuration = PUNCH_DURATION * 2; // не понятно почему так
        }
    }

    @Override
    public void highKick() {
        if (isIdle()) {
            highKickTime = 0;
            highKickDuration = HIGH_KICK_DURATION * 2; // не понятно почему так
        }
    }

    private boolean isIdle() {
        return !isPunch() && !isHighKick();
    }

    private boolean isPunch() {
        return punchDuration > 0;
    }

    private boolean isHighKick() {
        return highKickDuration > 0;
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

    private void animateHighKick() {
        highKickTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = highKickAnimation.getKeyFrame(highKickTime);
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
    private Animation<TextureRegion> createHighKickAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion highKickRegion = textureAtlas.findRegion("guileHighKick");
        TextureRegion[] punchFrames = new TextureRegion[5];
        punchFrames[0] = new TextureRegion(highKickRegion, 0, 0, 54, highKickRegion.getRegionHeight());
        punchFrames[1] = new TextureRegion(highKickRegion, 53, 0, 57, highKickRegion.getRegionHeight());
        punchFrames[2] = new TextureRegion(highKickRegion, 111, 0, 75, highKickRegion.getRegionHeight());
        punchFrames[3] = new TextureRegion(highKickRegion, 189, 0, 56, highKickRegion.getRegionHeight());
        punchFrames[4] = new TextureRegion(highKickRegion, 248, 0, highKickRegion.getRegionWidth() - 248, highKickRegion.getRegionHeight());

        return new Animation<>(PUNCH_DURATION, new Array<>(punchFrames), Animation.PlayMode.LOOP_PINGPONG);
    }
}
