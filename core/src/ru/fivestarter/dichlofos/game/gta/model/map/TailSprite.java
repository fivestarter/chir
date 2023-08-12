package ru.fivestarter.dichlofos.game.gta.model.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

public class TailSprite extends Sprite {
    private float minY;

    public TailSprite(TextureRegion textureRegion, float x, float y) {
        super(textureRegion);
        setSize(textureRegion.getRegionWidth() * WorldScreen.UNIT_SCALE, textureRegion.getRegionHeight() * WorldScreen.UNIT_SCALE);
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
