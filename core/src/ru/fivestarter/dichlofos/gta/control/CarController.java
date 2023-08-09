package ru.fivestarter.dichlofos.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import ru.fivestarter.dichlofos.gta.model.World;
import ru.fivestarter.dichlofos.gta.view.WorldScreen;

public class CarController {

    private final Sprite car;
    private final World world;

    float carSpeed;
    final float speedVelocity = 5f;
    final float speedMax = 20f;

    public CarController(Sprite car, World world) {
        this.car = car;
        this.world = world;
    }

    public void handle() {
        handleSpeed();
        handleRotation();
        handlePosition();
    }

    private void handlePosition() {
        float previousX = car.getX();
        float previousY = car.getY();
        float x = car.getX() + MathUtils.cosDeg(car.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF;
        float y = car.getY() + MathUtils.sinDeg(car.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF;
        car.setPosition(x, y);
        if (world.isBorderOverlapped(car.getBoundingRectangle())) {
            car.setPosition(previousX - MathUtils.cosDeg(car.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF,
                    previousY - MathUtils.sinDeg(car.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF);
            carSpeed /= -3;
        }
    }

    private void handleSpeed() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            carSpeed += speedVelocity * WorldScreen.DELTA_CFF;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            carSpeed -= speedVelocity * WorldScreen.DELTA_CFF;
        } else {
            downSpeed();
        }
        carSpeed = sliceSpeed();
    }

    private void downSpeed() {
        if (carSpeed > speedVelocity * WorldScreen.DELTA_CFF)
            carSpeed -= speedVelocity * WorldScreen.DELTA_CFF;
        else if (carSpeed < -speedVelocity * WorldScreen.DELTA_CFF)
            carSpeed += speedVelocity * WorldScreen.DELTA_CFF;
        else
            carSpeed = 0f;
    }

    private float sliceSpeed() {
        if (carSpeed > speedMax) {
            return speedMax;
        }
        return Math.max(carSpeed, -speedMax);
    }

    private void handleRotation() {
        float rotationSpeed = 30f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            car.rotate(rotationSpeed * carSpeed * WorldScreen.DELTA_CFF);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            car.rotate(-rotationSpeed * carSpeed * WorldScreen.DELTA_CFF);
        }
    }
}
