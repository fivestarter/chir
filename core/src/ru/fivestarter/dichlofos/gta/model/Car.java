package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.gta.control.CarController;

public class Car extends CentralSprite {

    private final CarController carController;

    public Car(TextureRegion textureRegion, World world, float x, float y, float with, float height) {
        super(textureRegion, x, y, with, height);
        carController = new CarController(this, world);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        carController.handle();
    }
}
