package ru.fivestarter.dichlofos.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static ru.fivestarter.dichlofos.view.WorldScreen.UNIT_SCALE;

public class Mercedes extends Car{

    private static final float with = 20f * UNIT_SCALE;
    private static final float height = 20f * 1.77f * UNIT_SCALE;

    public Mercedes(TextureRegion textureRegion, World world, float x, float y) {
        super(textureRegion, world, x, y, with, height);
    }
}
