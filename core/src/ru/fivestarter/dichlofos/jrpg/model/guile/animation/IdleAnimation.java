package ru.fivestarter.dichlofos.jrpg.model.guile.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;

import java.util.Arrays;
import java.util.stream.Stream;

public class IdleAnimation extends CharacterAnimation {
    public static final float IDLE_DURATION = 0.25f;

    public IdleAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, createIdleAnimation(textureAtlas), unitScale);
    }

    private static Animation<TextureRegion> createIdleAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion("guileIdle");
        TextureRegion[] walkFrames = Arrays.stream(idleRegion.split(idleRegion.getRegionWidth() / 3, idleRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new com.badlogic.gdx.graphics.g2d.Animation<>(IDLE_DURATION, new Array<>(walkFrames), com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
    }

    @Override
    protected boolean isFinish() {
        return true;
    }
}
