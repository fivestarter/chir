package ru.fivestarter.dichlofos.gta.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class TailSprite extends Sprite {
    private float minY;

    public TailSprite(TextureRegion textureRegion, float x, float y) {
        super(textureRegion);
        setSize(textureRegion.getRegionWidth() * UNIT_SCALE, textureRegion.getRegionHeight() * UNIT_SCALE);
        setPosition(x, y);
        this.minY = y;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMinY() {
        return minY;
    }
}
