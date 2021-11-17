package ru.fivestarter.dichlofos.jrpg.model.guile.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.jrpg.animation.CharacterAnimation;

import java.util.Arrays;
import java.util.stream.Stream;

public class WalkAnimation extends CharacterAnimation {
    public static final float WALK_DURATION = 0.25f;

    protected WalkAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale) {
        super(animationState, createWalkAnimation(textureAtlas), unitScale);
    }

    @Override
    public void animate(Sprite sprite) {
        super.animate(sprite);

        if (isFinish()) {
            idle();
        }

    }

    @Override
    protected boolean isFinish() {
        return false;
    }

    private static Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion walkRegion = textureAtlas.findRegion("guileWalk");
        TextureRegion[] walkFrames = Arrays.stream(walkRegion.split(walkRegion.getRegionWidth() / 5, walkRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(WALK_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP);
    }
}
