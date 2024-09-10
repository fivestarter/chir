package ru.fivestarter.dichlofos.game.gta.animation.character;

import com.badlogic.gdx.Gdx;
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
    private float animationTime = 0f;
    private boolean isMoving = false;
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
        createMoveAnimations(textureAtlas.findRegion(WALK_SPRITE_NAME));
        createRunAnimations(textureAtlas.findRegion(RUN_SPRITE_NAME));
        currentAnimation = moveDownAnimation;
    }

    private void createMoveAnimations(TextureRegion region) {
        TextureRegion[][] frames = region.split(region.getRegionWidth() / FRAMES_IN_ROW,
                region.getRegionHeight() / 4);
        moveUpAnimation = createAnimation(frames[0], frames[0][0]);
        moveDownAnimation = createAnimation(frames[1], frames[1][0]);
        moveLeftAnimation = createAnimation(frames[2], frames[2][0]);
        moveRightAnimation = createAnimation(frames[3], frames[3][0]);
    }

    private void createRunAnimations(TextureRegion region) {
        TextureRegion[][] frames = region.split(region.getRegionWidth() / FRAMES_IN_ROW,
                region.getRegionHeight() / 4);
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

    public TextureRegion getKeyFrame() {
        TextureRegion keyFrame;
        if (isMoving) {
            animationTime += Gdx.graphics.getDeltaTime();
            keyFrame = currentAnimation.getKeyFrame(animationTime);
        } else {
            animationTime = 0f;
            keyFrame = currentAnimation.getCalmFrame();
        }
        return keyFrame;
    }

    public void moveLeft() {
        isMoving = true;
        currentAnimation = moveLeftAnimation;
    }

    public void runLeft() {
        isMoving = true;
        currentAnimation = runLeftAnimation;
    }

    public void moveRight() {
        isMoving = true;
        currentAnimation = moveRightAnimation;
    }

    public void runRight() {
        isMoving = true;
        currentAnimation = runRightAnimation;
    }

    public void moveUp() {
        isMoving = true;
        currentAnimation = moveUpAnimation;
    }

    public void runUp() {
        isMoving = true;
        currentAnimation = runUpAnimation;
    }

    public void moveDown() {
        isMoving = true;
        currentAnimation = moveDownAnimation;
    }

    public void runDown() {
        isMoving = true;
        currentAnimation = runDownAnimation;
    }

    public void stop() {
        isMoving = false;
    }
}
