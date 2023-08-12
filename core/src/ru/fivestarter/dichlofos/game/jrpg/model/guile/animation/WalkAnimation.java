package ru.fivestarter.dichlofos.game.jrpg.model.guile.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.game.jrpg.animation.AnimationState;
import ru.fivestarter.dichlofos.game.jrpg.animation.CharacterAnimation;

import java.util.Arrays;
import java.util.stream.Stream;

public class WalkAnimation extends CharacterAnimation {
    public static final float WALK_DURATION = 0.25f;
    public static final float SPEED = 100f;

    private final float border;
    private boolean isFinish = false;

    public WalkAnimation(AnimationState animationState, TextureAtlas textureAtlas, float unitScale, float border) {
        super(animationState, createWalkAnimation(textureAtlas), unitScale);
        this.border = border;
    }

    @Override
    public void animate(Sprite sprite) {
        super.animate(sprite);
        float xPosition = sprite.getX() + SPEED * Gdx.graphics.getDeltaTime();
        if (xPosition > border) {
            xPosition = border;
            isFinish = true;
        }
        sprite.setPosition(xPosition, sprite.getY());

        if (isFinish()) {
            highKick();
        }
    }

    @Override
    protected boolean isFinish() {
        return isFinish;
    }

    private static Animation<TextureRegion> createWalkAnimation(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion walkRegion = textureAtlas.findRegion("guileWalk");
        TextureRegion[] walkFrames = Arrays.stream(walkRegion.split(walkRegion.getRegionWidth() / 5, walkRegion.getRegionHeight()))
                .flatMap(Stream::of)
                .toArray(TextureRegion[]::new);
        return new Animation<>(WALK_DURATION, new Array<>(walkFrames), Animation.PlayMode.LOOP);
    }
}
