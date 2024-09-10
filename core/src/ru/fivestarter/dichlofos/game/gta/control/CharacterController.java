package ru.fivestarter.dichlofos.game.gta.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface CharacterController {
    Sprite getModel();

    void draw(float delta, Batch batch);
}
