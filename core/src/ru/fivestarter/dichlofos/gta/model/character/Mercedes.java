package ru.fivestarter.dichlofos.gta.model.character;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.gta.model.World;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Mercedes extends Car {

    public static final String SPRITE_NAME = "car";
    private static final float WITH = 20f * UNIT_SCALE;
    private static final float HEIGHT = 20f * 1.77f * UNIT_SCALE;

    public Mercedes(TextureRegion textureRegion, World world, float x, float y) {
        super(textureRegion, world, x, y, WITH, HEIGHT);
    }
}
