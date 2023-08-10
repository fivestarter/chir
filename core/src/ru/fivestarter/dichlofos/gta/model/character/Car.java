package ru.fivestarter.dichlofos.gta.model.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import ru.fivestarter.dichlofos.gta.control.CarController;
import ru.fivestarter.dichlofos.gta.model.World;

import static com.badlogic.gdx.graphics.g2d.Batch.*;
import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Car extends CharacterSprite {

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

    public Polygon getPolygon() {
        float[] vertices = getVertices();
        float[] scaledVertices = new float[8];
        scaledVertices[0] = vertices[X1] / UNIT_SCALE;
        scaledVertices[1] = vertices[Y1] / UNIT_SCALE;
        scaledVertices[2] = vertices[X2] / UNIT_SCALE;
        scaledVertices[3] = vertices[Y2] / UNIT_SCALE;
        scaledVertices[4] = vertices[X3] / UNIT_SCALE;
        scaledVertices[5] = vertices[Y3] / UNIT_SCALE;
        scaledVertices[6] = vertices[X4] / UNIT_SCALE;
        scaledVertices[7] = vertices[Y4] / UNIT_SCALE;

        return new Polygon(scaledVertices);
    }
}
