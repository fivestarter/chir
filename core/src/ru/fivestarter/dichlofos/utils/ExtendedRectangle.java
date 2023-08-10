package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.math.Rectangle;

public class ExtendedRectangle extends Rectangle {

    public ExtendedRectangle(Rectangle rect) {
        super(rect);
    }

    public float[] getVertices() {
        float[] vertices = new float[8];
        vertices[0] = getX();
        vertices[1] = getY();
        vertices[2] = getX();
        vertices[3] = getY() + getHeight();
        vertices[4] = getX() + getWidth();
        vertices[5] = getY() + getHeight();
        vertices[6] = getX() + getWidth();
        vertices[7] = getY();
        return vertices;
    }
}
