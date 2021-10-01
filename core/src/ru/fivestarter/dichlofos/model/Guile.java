package ru.fivestarter.dichlofos.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.control.GuileController;

import java.util.Arrays;
import java.util.stream.Stream;

public class Guile implements Character {

    private final Animation<TextureRegion> walkAnimation;
    private final TextureRegion[] walkFrames;
    private final Sprite sprite;
    private float stateTime = 0f;
    private GuileController guileController;

    public Guile(TextureRegion textureRegion, int x, int y, int with, int height) {
        this.walkFrames = Arrays.stream(textureRegion.split(textureRegion.getRegionWidth() / 3, textureRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        walkAnimation = new Animation<>(0.25f, new Array<>(walkFrames), Animation.PlayMode.LOOP_PINGPONG);
        this.sprite = new Sprite(walkAnimation.getKeyFrame(stateTime, true), x, y, with, height);
        this.sprite.setSize(with, height);
        this.sprite.setPosition(x, y);
        this.guileController = new GuileController(this);
    }

    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(walkAnimation.getKeyFrame(stateTime));
        sprite.draw(batch);
    }

    @Override
    public void punch() {
        //
    }
}
