package ru.fivestarter.dichlofos.game.gta.animation.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.game.gta.animation.TextureAnimation;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public class HeroAnimation {

    public static final String WALK_SPRITE_NAME = "mh_walk";
    public static final String RUN_SPRITE_NAME = "mh_run";
    public static final float DURATION = 0.15f;
    public static final int FRAMES_IN_ROW = 3;
    private TextureAnimation moveLeftAnimation;
    private TextureAnimation moveRightAnimation;
    private TextureAnimation moveDownAnimation;
    private TextureAnimation moveUpAnimation;
    private TextureAnimation currentAnimation;
    private TextureAnimation runLeftAnimation;
    private TextureAnimation runRightAnimation;
    private TextureAnimation runDownAnimation;
    private TextureAnimation runUpAnimation;

    public HeroAnimation(TextureAtlas textureAtlas) {
        createMoveAnimations(textureAtlas);
        createRunAnimations(textureAtlas);
    }

    private void createMoveAnimations(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion(WALK_SPRITE_NAME);
        TextureRegion[][] frames = idleRegion.split(idleRegion.getRegionWidth() / FRAMES_IN_ROW,
                idleRegion.getRegionHeight() / 4);
        moveUpAnimation = createAnimation(frames[0], frames[0][0]);
        moveDownAnimation = createAnimation(frames[1], frames[1][0]);
        moveLeftAnimation = createAnimation(frames[2], frames[2][0]);
        moveRightAnimation = createAnimation(frames[3], frames[3][0]);
    }

    private void createRunAnimations(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion(RUN_SPRITE_NAME);
        TextureRegion[][] frames = idleRegion.split(idleRegion.getRegionWidth() / FRAMES_IN_ROW,
                idleRegion.getRegionHeight() / 4);
        runUpAnimation = createAnimation(frames[0], moveUpAnimation.getCalmFrame());
        runDownAnimation = createAnimation(frames[1], moveDownAnimation.getCalmFrame());
        runLeftAnimation = createAnimation(frames[2], moveLeftAnimation.getCalmFrame());
        runRightAnimation = createAnimation(frames[3], moveRightAnimation.getCalmFrame());
    }

    private TextureAnimation createAnimation(TextureRegion[] frames, TextureRegion calmFrame) {
        Array<TextureRegion> upTextureRegions = new Array<>();
        upTextureRegions.addAll(frames[2], frames[0], frames[1], frames[0]);
        return new TextureAnimation(DURATION, upTextureRegions, calmFrame, LOOP);
    }

    public TextureAnimation getMoveLeftAnimation() {
        return moveLeftAnimation;
    }

    public TextureAnimation getMoveRightAnimation() {
        return moveRightAnimation;
    }

    public TextureAnimation getMoveDownAnimation() {
        return moveDownAnimation;
    }

    public TextureAnimation getMoveUpAnimation() {
        return moveUpAnimation;
    }

    public TextureAnimation getCurrentAnimation() {
        return currentAnimation;
    }

    public TextureAnimation getRunLeftAnimation() {
        return runLeftAnimation;
    }

    public TextureAnimation getRunRightAnimation() {
        return runRightAnimation;
    }

    public TextureAnimation getRunDownAnimation() {
        return runDownAnimation;
    }

    public TextureAnimation getRunUpAnimation() {
        return runUpAnimation;
    }
}
