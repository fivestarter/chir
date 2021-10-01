package ru.fivestarter.dichlofos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile {

    private final Animation<TextureRegion> walkAnimation; // #3
    private final TextureRegion[] walkFrames; // #5
    private final float x;
    private final float y;
    private final float with;
    private final float height;
    private TextureRegion currentFrame; // #7
    float stateTime;

    public Guile(TextureRegion textureRegion, float x, float y, float with, float height) {
        walkFrames = Arrays.stream(textureRegion.split(textureRegion.getRegionWidth()/3, textureRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        this.x = x;
        this.y = y;
        this.with = with;
        this.height = height;
        walkAnimation = new Animation<>(0.25f, walkFrames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // #15
        currentFrame = walkAnimation.getKeyFrame(stateTime, true); // #16
        batch.draw(currentFrame, x, y, with, height);
    }
}
