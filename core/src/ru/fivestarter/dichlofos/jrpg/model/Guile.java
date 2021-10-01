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
    private Animation<TextureRegion> currentAnimation;

    private final Sprite sprite;
    private float stateTime = 0f;
    private float activeStateTime = 0f;
    private GuileController guileController;

    public Guile(TextureAtlas textureAtlas, int x, int y, int with, int height) {
        this.idleAnimation = createWalkAnimation(textureAtlas);
        this.punchAnimation = createPunchAnimation(textureAtlas);
        this.currentAnimation = idleAnimation;
        this.sprite = new Sprite(idleAnimation.getKeyFrame(stateTime, true), x, y, with, height);
        this.sprite.setSize(with, height);
        this.sprite.setPosition(x, y);
        this.guileController = new GuileController(this);
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(currentAnimation.getKeyFrame(stateTime));
        sprite.draw(batch);
        guileController.handle();

        if (isActiveState()) {
            activeStateTime -= Gdx.graphics.getDeltaTime();
            if (!isActiveState()) {
                currentAnimation = idleAnimation;
            }
        }
    }

    @Override
    public void punch() {
        if (!isActiveState()) {
            currentAnimation = punchAnimation;
            activeStateTime = FRAME_DURATION;
        }
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

    private boolean isActiveState() {
        return activeStateTime > 0;
    }
}
