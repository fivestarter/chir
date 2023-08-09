package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CentralSprite extends Sprite {
    public CentralSprite(TextureRegion textureRegion, float x, float y, float with, float height) {
        super(textureRegion);
        setSize(with, height);
        setOrigin(with / 2, height / 2);
        setPosition(x, y);
    }
}
