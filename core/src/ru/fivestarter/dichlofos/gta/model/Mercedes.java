package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Mercedes extends Car {

    public static final String SPRITE_NAME = "car";
    private static final float with = 20f * UNIT_SCALE;
    private static final float height = 20f * 1.77f * UNIT_SCALE;

    public Mercedes(TextureRegion textureRegion, World world, float x, float y) {
        super(textureRegion, world, x, y, with, height);
    }
}
