package ru.fivestarter.dichlofos.jrpg.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.stream.Stream;

public class Honda {
    public static final float IDLE_DURATION = 0.25f;
    private static final int UNIT_SCALE = 6;

    private final Animation<TextureRegion> idleAnimation;

    private final Sprite sprite;
    private float idleTime = 0f;

    public Honda(TextureAtlas textureAtlas, int x, int y) {
        idleAnimation = createWalkAnimation(textureAtlas);

        this.sprite = new Sprite();
        this.sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        animateIdle();
        sprite.draw(batch);
    }

    private Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion("hondaIdle");
        TextureRegion[] walkFrames = Arrays.stream(idleRegion.split(idleRegion.getRegionWidth() / 4, idleRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .peek(textureRegion -> textureRegion.flip(true, false))
                .toArray(TextureRegion[]::new);
        return new Animation<>(IDLE_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP_PINGPONG);
    }

    private void animateIdle() {
        idleTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = idleAnimation.getKeyFrame(idleTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * UNIT_SCALE, keyFrame.getRegionHeight() * UNIT_SCALE);
    }

}
