package ru.fivestarter.dichlofos.game.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface CharacterController {
    Sprite getModel();

    void draw(Batch batch);
}
