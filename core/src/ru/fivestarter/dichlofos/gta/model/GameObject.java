package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public abstract class GameObject {
    protected Polygon bounds;
    private final Sprite object;

    public GameObject(TextureRegion textureRegion, float x, float y, float with, float height) {
        object = new Sprite(textureRegion);
        object.setSize(with, height);
        object.setOrigin(with / 2, height / 2);
        object.setPosition(x, y);

        bounds = new Polygon(new float[]{0, 0, with, 0, with, height, 0, height});
        bounds.setPosition(x, y);
        bounds.setOrigin(with / 2, height / 2);
    }

    public void draw(SpriteBatch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }

    public Polygon getBounds() {
        return bounds;
    }
}
