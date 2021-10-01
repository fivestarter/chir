package ru.fivestarter.dichlofos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile {

    private final Animation<TextureRegion> walkAnimation;
    private final TextureRegion[] walkFrames;
    private final Sprite sprite;
    float stateTime = 0f;

    public Guile(TextureRegion textureRegion, int x, int y, int with, int height) {
        walkFrames = Arrays.stream(textureRegion.split(textureRegion.getRegionWidth()/3, textureRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        walkAnimation = new Animation<>(0.25f, walkFrames);
        sprite = new Sprite(walkAnimation.getKeyFrame(stateTime, true), x, y, with, height);
        sprite.setSize(with, height);
        sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        sprite.draw(batch);
    }
}
