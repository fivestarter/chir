package ru.fivestarter.dichlofos.game.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Controller {
    Sprite getModel();

    void draw(float delta, Batch batch);
}
