package ru.fivestarter.dichlofos.game.gta.model.character.car;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.game.gta.model.character.CharacterSprite;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

public class Car extends CharacterSprite {

    public static final String SPRITE_NAME = "car";
    private static final float WITH = 20f * WorldScreen.UNIT_SCALE;
    private static final float HEIGHT = 20f * 1.77f * WorldScreen.UNIT_SCALE;

    public Car(TextureRegion textureRegion, float x, float y) {
        super(textureRegion, x, y, WITH, HEIGHT);
    }

}
