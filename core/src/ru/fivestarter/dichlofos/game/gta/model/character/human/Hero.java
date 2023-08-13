package ru.fivestarter.dichlofos.game.gta.model.character.human;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.gta.animation.character.HeroAnimation;
import ru.fivestarter.dichlofos.game.gta.model.character.CharacterSprite;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

public class Hero extends CharacterSprite {

    private static final float DIMENSION = 20f * WorldScreen.UNIT_SCALE;
    private final HeroAnimation heroAnimation;

    public Hero(TextureAtlas textureAtlas, float x, float y) {
        super(x, y, DIMENSION, DIMENSION);
        heroAnimation = new HeroAnimation(textureAtlas);
    }

    @Override
    public void draw(Batch batch) {
        setRegion(heroAnimation.getKeyFrame());
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
        setX(distance);
        heroAnimation.moveLeft();
    }

    public void runLeft(float distance) {
        setX(distance);
        heroAnimation.runLeft();
    }

    public void moveRight(float distance) {
        setX(distance);
        heroAnimation.moveRight();
    }

    public void runRight(float distance) {
        setX(distance);
        heroAnimation.runRight();
    }

    public void moveUp(float distance) {
        setY(distance);
        heroAnimation.moveUp();
    }

    public void runUp(float distance) {
        setY(distance);
        heroAnimation.runUp();
    }


    public void moveDown(float distance) {
        setY(distance);
        heroAnimation.moveDown();
    }

    public void runDown(float distance) {
        setY(distance);
        heroAnimation.runDown();
    }

    public void stop() {
        heroAnimation.stop();
    }
}
