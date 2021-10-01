package ru.fivestarter.dichlofos.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import ru.fivestarter.dichlofos.gta.model.World;
import ru.fivestarter.dichlofos.gta.view.WorldScreen;

public class CarController {

    private final Polygon carBounds;
    private final World world;

    float carSpeed;
    final float speedVelocity = 5f;
    final float speedMax = 20f;

    final private float rotationSpeed = 30f;

    public CarController(Polygon carBounds, World world) {
        this.carBounds = carBounds;
        this.world = world;
    }

    public void handle() {
        handleSpeed();
        handleRotation();
        handlePosition();
    }

    private void handlePosition() {
        float previousX = carBounds.getX();
        float previousY = carBounds.getY();
        float x = carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF;
        float y = carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF;
        carBounds.setPosition(x, y);
        if (world.isBorderOverlapped(carBounds.getBoundingRectangle())) {
            carBounds.setPosition(previousX - MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF,
                    previousY - MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * WorldScreen.DELTA_CFF);
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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            carBounds.rotate(rotationSpeed * carSpeed * WorldScreen.DELTA_CFF);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            carBounds.rotate(-rotationSpeed * carSpeed * WorldScreen.DELTA_CFF);
        }
    }
}
