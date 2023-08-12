package ru.fivestarter.dichlofos.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.fivestarter.dichlofos.gta.model.World;
import ru.fivestarter.dichlofos.gta.model.character.car.Car;
import ru.fivestarter.dichlofos.gta.view.WorldScreen;

import static ru.fivestarter.dichlofos.gta.model.character.car.Car.SPRITE_NAME;
import static ru.fivestarter.dichlofos.utils.Assets.COMMON_ATLAS_FILE_NAME;

public class CarController implements CharacterController {
    private static final int X = 40;
    private static final int Y = 49;

    private final Car car;
    private final World world;

    float carSpeed;
    final float speedVelocity = 5f;
    final float speedMax = 20f;

    public CarController(AssetManager assetManager, World world) {
        TextureAtlas.AtlasRegion region = assetManager.get(COMMON_ATLAS_FILE_NAME, TextureAtlas.class)
                .findRegion(SPRITE_NAME);
        this.car = new Car(region, X, Y);
        this.world = world;
    }

    @Override
    public Car getModel() {
        return car;
    }

    @Override
    public void draw(Batch batch) {
        car.draw(batch);
        handle();
    }

    private void handle() {
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
        if (world.isObstacle(car.getPolygon())) {
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
