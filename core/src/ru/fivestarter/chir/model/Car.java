package ru.fivestarter.chir.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fivestarter.chir.control.CarController;

public class Car extends GameObject {

    private CarController carController;

    public Car(Texture texture, float x, float y, float with, float height) {
        super(texture, x, y, with, height);
        carController = new CarController(bounds);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        carController.handle();
    }
}
