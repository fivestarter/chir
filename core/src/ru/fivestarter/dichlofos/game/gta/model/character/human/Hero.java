package ru.fivestarter.dichlofos.game.gta.model.character.human;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.gta.animation.TextureAnimation;
import ru.fivestarter.dichlofos.game.gta.animation.character.HeroAnimation;
import ru.fivestarter.dichlofos.game.gta.model.character.CharacterSprite;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

public class Hero extends CharacterSprite {

    private static final float DIMENSION = 20f * WorldScreen.UNIT_SCALE;

    private float animationTime = 0f;

    private TextureAnimation currentAnimation;

    private boolean isMoving = false;
    private final HeroAnimation heroAnimation;


    public Hero(TextureAtlas textureAtlas, float x, float y) {
        super(x, y, DIMENSION, DIMENSION);
        heroAnimation = new HeroAnimation(textureAtlas);
        currentAnimation = heroAnimation.getMoveDownAnimation();
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
        left(distance, heroAnimation.getMoveLeftAnimation());
    }

    public void runLeft(float distance) {
        left(distance, heroAnimation.getRunLeftAnimation());
    }

    private void left(float distance, TextureAnimation animation) {
        isMoving = true;
        setX(distance);
        currentAnimation = animation;
    }

    public void moveRight(float distance) {
        right(distance, heroAnimation.getMoveRightAnimation());
    }

    public void runRight(float distance) {
        right(distance, heroAnimation.getRunRightAnimation());
    }

    private void right(float distance, TextureAnimation animation) {
        isMoving = true;
        setX(distance);
        currentAnimation = animation;
    }

    public void moveUp(float distance) {
        up(distance, heroAnimation.getMoveUpAnimation());
    }

    public void runUp(float distance) {
        up(distance, heroAnimation.getRunUpAnimation());
    }

    private void up(float distance, TextureAnimation animation) {
        isMoving = true;
        setY(distance);
        currentAnimation = animation;
    }

    public void moveDown(float distance) {
        down(distance, heroAnimation.getMoveDownAnimation());
    }

    public void runDown(float distance) {
        down(distance, heroAnimation.getRunDownAnimation());
    }

    private void down(float distance, TextureAnimation animation) {
        isMoving = true;
        setY(distance);
        currentAnimation = animation;
    }

    public void stop() {
        isMoving = false;
    }
}
