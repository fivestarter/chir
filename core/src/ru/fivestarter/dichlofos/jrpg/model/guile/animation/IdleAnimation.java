package ru.fivestarter.dichlofos.jrpg.model.guile.animation;

import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;
import ru.fivestarter.dichlofos.jrpg.model.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.stream.Stream;

public class IdleAnimation extends CharacterAnimation {
    public static final float IDLE_DURATION = 0.25f;
    private final Animation<TextureRegion> animation;

    public IdleAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, unitScale);
        this.animation = createIdleAnimation(textureAtlas);
    }

    private Animation<TextureRegion> createIdleAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion("guileIdle");
        TextureRegion[] walkFrames = Arrays.stream(idleRegion.split(idleRegion.getRegionWidth() / 3, idleRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new com.badlogic.gdx.graphics.g2d.Animation<>(IDLE_DURATION, new Array<>(walkFrames), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
    }

    @Override
    public void animate(Sprite sprite) {
        animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = animation.getKeyFrame(animationTime);
        sprite.setRegion(keyFrame);
        sprite.setSize(keyFrame.getRegionWidth() * unitScale, keyFrame.getRegionHeight() * unitScale);
    }

    @Override
    protected boolean isFinish() {
        return true;
    }

    @Override
    protected void reset() {
    }
}
