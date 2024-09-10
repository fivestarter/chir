package ru.fivestarter.dichlofos.game.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.fivestarter.dichlofos.game.gta.model.World;
import ru.fivestarter.dichlofos.game.gta.model.character.car.Car;

public class CarController implements CharacterController {

    private final Car car;
    private final World world;

    float carSpeed;
    final float speedVelocity = 5f;
    final float speedMax = 20f;

    public CarController(TextureRegion region, World world, float x, float y) {
        this.car = new Car(region, x, y);
        this.world = world;
    }

    @Override
    public Car getModel() {
        return car;
    }

    @Override
    public void draw(float delta, Batch batch) {
        car.draw(batch);
        handle(delta);
    }

    private void handle(float delta) {
        handleSpeed(delta);
        handleRotation(delta);
        handlePosition(delta);
    }

    private void handlePosition(float delta) {
        float previousX = car.getX();
        float previousY = car.getY();
        float x = car.getX() + MathUtils.cosDeg(car.getRotation() + 90) * carSpeed * delta;
        float y = car.getY() + MathUtils.sinDeg(car.getRotation() + 90) * carSpeed * delta;
        car.setPosition(x, y);
        if (world.isObstacle(car.getPolygon())) {
            car.setPosition(previousX - MathUtils.cosDeg(car.getRotation() + 90) * carSpeed * delta,
                    previousY - MathUtils.sinDeg(car.getRotation() + 90) * carSpeed * delta);
            carSpeed /= -3;
        }
    }

    private void handleSpeed(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            carSpeed += speedVelocity * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            carSpeed -= speedVelocity * delta;
        } else {
            downSpeed(delta);
        }
        carSpeed = sliceSpeed();
    }

    private void downSpeed(float delta) {
        if (carSpeed > speedVelocity * delta)
            carSpeed -= speedVelocity * delta;
        else if (carSpeed < -speedVelocity * delta)
            carSpeed += speedVelocity * delta;
        else
            carSpeed = 0f;
    }

    private float sliceSpeed() {
        if (carSpeed > speedMax) {
            return speedMax;
        }
        return Math.max(carSpeed, -speedMax);
    }

    private void handleRotation(float delta) {
        float rotationSpeed = 30f;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            car.rotate(rotationSpeed * carSpeed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            car.rotate(-rotationSpeed * carSpeed * delta);
        }
    }

}
