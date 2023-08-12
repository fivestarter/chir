package ru.fivestarter.dichlofos.game.gta.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

import static com.badlogic.gdx.graphics.g2d.Batch.*;
import static ru.fivestarter.dichlofos.game.gta.view.WorldScreen.UNIT_SCALE;

public class CharacterSprite extends Sprite {

    public CharacterSprite(float x, float y, float with, float height) {
        setSize(with, height);
        setOrigin(with / 2, height / 2);
        setPosition(x, y);
    }

    public CharacterSprite(TextureRegion textureRegion, float x, float y, float with, float height) {
        this(x, y, with, height);
        setRegion(textureRegion);
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
