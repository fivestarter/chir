package ru.fivestarter.chir.control;

import com.badlogic.gdx.math.Polygon;

public class CarController {

    private Polygon carBounds;

    public CarController(Polygon carBounds) {
        this.carBounds = carBounds;
    }

    public void handle() {
        carBounds.setPosition(carBounds.getX(), carBounds.getY() - 0.02f);
    }
}
