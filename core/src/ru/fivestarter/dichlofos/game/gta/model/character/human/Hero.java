package ru.fivestarter.dichlofos.game.gta.model.character.human;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.game.gta.animation.HeroAnimation;
import ru.fivestarter.dichlofos.game.gta.model.character.CharacterSprite;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public class Hero extends CharacterSprite {

    public static final String WALK_SPRITE_NAME = "mh_walk";
    public static final String RUN_SPRITE_NAME = "mh_run";
    private static final float DIMENSION = 20f * WorldScreen.UNIT_SCALE;

    public static final float DURATION = 0.15f;
    public static final int FRAMES_IN_ROW = 3;
    private float animationTime = 0f;
    private HeroAnimation moveLeftAnimation;
    private HeroAnimation moveRightAnimation;
    private HeroAnimation moveDownAnimation;
    private HeroAnimation moveUpAnimation;
    private HeroAnimation currentAnimation;
    private HeroAnimation runLeftAnimation;
    private HeroAnimation runRightAnimation;
    private HeroAnimation runDownAnimation;
    private HeroAnimation runUpAnimation;

    private boolean isMoving = false;


    public Hero(TextureAtlas textureAtlas, float x, float y) {
        super(x, y, DIMENSION, DIMENSION);
        createAnimations(textureAtlas);
        createRunAnimations(textureAtlas);
        currentAnimation = moveDownAnimation;
    }

    private void createAnimations(TextureAtlas textureAtlas) {
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

    private HeroAnimation createAnimation(TextureRegion[] frames, TextureRegion calmFrame) {
        Array<TextureRegion> upTextureRegions = new Array<>();
        upTextureRegions.addAll(frames[2], frames[0], frames[1], frames[0]);
        return new HeroAnimation(DURATION, upTextureRegions, calmFrame, LOOP);
    }

    @Override
    public void draw(Batch batch) {
        TextureRegion keyFrame;
        if (isMoving) {
            animationTime += Gdx.graphics.getDeltaTime();
            keyFrame = currentAnimation.getKeyFrame(animationTime);
        } else {
            animationTime = 0f;
            keyFrame = currentAnimation.getCalmFrame();
        }
        setRegion(keyFrame);
        super.draw(batch);
    }

    public Rectangle getFootRectangle() {
        Rectangle rectangle = getBoundingRectangle();
        rectangle.setHeight(DIMENSION / 4);
        rectangle.setX(rectangle.getX() + DIMENSION / 8);
        rectangle.setWidth(DIMENSION - DIMENSION / 4);
        return rectangle;
    }

    public void moveLeft(float distance) {
        left(distance, moveLeftAnimation);
    }

    public void runLeft(float distance) {
        left(distance, runLeftAnimation);
    }

    private void left(float distance, HeroAnimation animation) {
        isMoving = true;
        setX(distance);
        currentAnimation = animation;
    }

    public void moveRight(float distance) {
        right(distance, moveRightAnimation);
    }

    public void runRight(float distance) {
        right(distance, runRightAnimation);
    }

    private void right(float distance, HeroAnimation animation) {
        isMoving = true;
        setX(distance);
        currentAnimation = animation;
    }

    public void moveUp(float distance) {
        up(distance, moveUpAnimation);
    }

    public void runUp(float distance) {
        up(distance, runUpAnimation);
    }

    private void up(float distance, HeroAnimation animation) {
        isMoving = true;
        setY(distance);
        currentAnimation = animation;
    }

    public void moveDown(float distance) {
        down(distance, moveDownAnimation);
    }

    public void runDown(float distance) {
        down(distance, runDownAnimation);
    }

    private void down(float distance, HeroAnimation animation) {
        isMoving = true;
        setY(distance);
        currentAnimation = animation;
    }

    public void stop() {
        isMoving = false;
    }
}
