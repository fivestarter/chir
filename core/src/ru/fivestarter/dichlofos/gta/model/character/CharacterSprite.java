package ru.fivestarter.dichlofos.gta.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterSprite extends Sprite {
    public CharacterSprite(TextureRegion textureRegion, float x, float y, float with, float height) {
        super(textureRegion);
        setSize(with, height);
        setOrigin(with / 2, height / 2);
        setPosition(x, y);
    }
}
