package ru.fivestarter.chir.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import ru.fivestarter.chir.view.GameScreen;

public class CarController {

    private final Polygon carBounds;

    float carSpeed;
    final float speedVelocity = 10f;
    final float speedMax = 10f;

    final private float rotationSpeed = 30f;

    public CarController(Polygon carBounds) {
        this.carBounds = carBounds;
    }

    public void handle() {
        handleSpeed();
        handleRotation();

        carBounds.setPosition(carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.DELTA_CFF,
                carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.DELTA_CFF);
    }

    private void handleSpeed() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            carSpeed += speedVelocity * GameScreen.DELTA_CFF;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            carSpeed -= speedVelocity * GameScreen.DELTA_CFF;
        } else {
            downSpeed();
        }
        carSpeed = sliceSpeed();
    }

    private void downSpeed() {
        if (carSpeed > speedVelocity * GameScreen.DELTA_CFF)
            carSpeed -= speedVelocity * GameScreen.DELTA_CFF;
        else if (carSpeed < -speedVelocity * GameScreen.DELTA_CFF)
            carSpeed += speedVelocity * GameScreen.DELTA_CFF;
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
            carBounds.rotate(rotationSpeed * carSpeed * GameScreen.DELTA_CFF);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            carBounds.rotate(-rotationSpeed * carSpeed * GameScreen.DELTA_CFF);
        }
    }
}
