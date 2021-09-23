package ru.fivestarter.chir.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import ru.fivestarter.chir.view.GameScreen;

import java.util.stream.StreamSupport;

import static ru.fivestarter.chir.view.GameScreen.UNIT_SCALE;

public class CarController {

    private final Polygon carBounds;
    private final TiledMap map;

    float carSpeed;
    final float speedVelocity = 5f;
    final float speedMax = 20f;

    final private float rotationSpeed = 30f;

    public CarController(Polygon carBounds, TiledMap map) {
        this.carBounds = carBounds;
        this.map = map;
    }

    public void handle() {
        handleSpeed();
        handleRotation();
        handlePosition();
    }

    private void handlePosition() {
        float x = carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.DELTA_CFF;
        float y = carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.DELTA_CFF;
        if (!isBorderTouched(x, y)) {
            carBounds.setPosition(x, y);
        } else {
            carSpeed = 0;
        }
    }

    private boolean isBorderTouched(float x, float y) {
        return StreamSupport.stream(map.getLayers().get("Слой объектов 1").getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().contains(x / UNIT_SCALE, y / UNIT_SCALE));
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
