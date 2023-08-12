package ru.fivestarter.dichlofos.game.gta.model.character.human;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import ru.fivestarter.dichlofos.game.gta.model.character.CharacterSprite;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public class Hero extends CharacterSprite {

    public static final String SPRITE_NAME = "mh_walk";
    private static final float DIMENSION = 20f * WorldScreen.UNIT_SCALE;

    public static final float DURATION = 0.15f;
    public static final int FRAMES_IN_ROW = 3;
    private float animationTime = 0f;
    private Animation<TextureRegion> leftAnimation;
    private Animation<TextureRegion> rightAnimation;
    private Animation<TextureRegion> downAnimation;
    private Animation<TextureRegion> upAnimation;
    private Animation<TextureRegion> currentAnimation;

    private boolean isMoving = false;


    public Hero(TextureAtlas textureAtlas, float x, float y) {
        super(x, y, DIMENSION, DIMENSION);
        createAnimations(textureAtlas);
        currentAnimation = downAnimation;
    }

    private void createAnimations(TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion idleRegion = textureAtlas.findRegion(SPRITE_NAME);
        TextureRegion[][] frames = idleRegion.split(idleRegion.getRegionWidth() / FRAMES_IN_ROW,
                idleRegion.getRegionHeight() / 4);
        upAnimation = createAnimation(frames[0]);
        downAnimation = createAnimation(frames[1]);
        leftAnimation = createAnimation(frames[2]);
        rightAnimation = createAnimation(frames[3]);
    }

    private Animation<TextureRegion> createAnimation(TextureRegion[] frames) {
        Array<TextureRegion> upTextureRegions = new Array<>();
        upTextureRegions.addAll(frames[2], frames[0], frames[1], frames[0]);
        return new Animation<>(DURATION, upTextureRegions, LOOP);
    }

    @Override
    public void draw(Batch batch) {
        if (isMoving) {
            animationTime += Gdx.graphics.getDeltaTime();
        } else {
            animationTime = FRAMES_IN_ROW - 1;
        }
        TextureRegion keyFrame = currentAnimation.getKeyFrame(animationTime);
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
        isMoving = true;
        setX(distance);
        currentAnimation = leftAnimation;
    }

    public void moveRight(float distance) {
        isMoving = true;
        setX(distance);
        currentAnimation = rightAnimation;
    }

    public void moveUp(float distance) {
        isMoving = true;
        setY(distance);
        currentAnimation = upAnimation;
    }

    public void moveDown(float distance) {
        isMoving = true;
        setY(distance);
        currentAnimation = downAnimation;
    }

    public void stop() {
        isMoving = false;
    }
}
