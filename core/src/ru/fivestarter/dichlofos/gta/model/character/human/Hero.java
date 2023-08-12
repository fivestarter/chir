package ru.fivestarter.dichlofos.gta.model.character.human;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.CharacterSprite;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Hero extends CharacterSprite {

    public static final String SPRITE_NAME = "mh_walk";
    private static final float DIMENSION = 20f * UNIT_SCALE;


    public Hero(TextureRegion textureRegion, float x, float y) {
        super(textureRegion.split(textureRegion.getRegionWidth() / 3, textureRegion.getRegionHeight() / 4)[1][0],
                x,
                y,
                DIMENSION,
                DIMENSION);
    }


    public Rectangle getFootRectangle() {
        Rectangle rectangle = getBoundingRectangle();
        rectangle.setHeight(DIMENSION / 4);
        rectangle.setX(rectangle.getX() + DIMENSION / 8);
        rectangle.setWidth(DIMENSION - DIMENSION / 4);
        return rectangle;
    }
}
