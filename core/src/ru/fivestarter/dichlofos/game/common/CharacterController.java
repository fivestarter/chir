package ru.fivestarter.dichlofos.game.common;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface CharacterController<T> {
    T getModel();

    void draw(Batch batch);
}
