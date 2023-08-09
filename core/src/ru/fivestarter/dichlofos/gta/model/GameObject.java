package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameObject {
    protected final Sprite sprite;

    public GameObject(TextureRegion textureRegion, float x, float y, float with, float height) {
        sprite = new Sprite(textureRegion);
        sprite.setSize(with, height);
        sprite.setOrigin(with / 2, height / 2);
        sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
