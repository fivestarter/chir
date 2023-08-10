package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public final class IntersectorUtil {
    private IntersectorUtil() {
    }

    public static boolean overlapConvexPolygons(Polygon polygon, Rectangle rectangle) {
        ExtendedRectangle extendedRectangle = new ExtendedRectangle(rectangle);
        Polygon borderPolygon = new Polygon(extendedRectangle.getVertices());

        boolean result = false;
        try {
            result = Intersector.overlapConvexPolygons(polygon, borderPolygon);
        } catch (Exception e) {
            Gdx.app.error("Warn", "Ошибка при расчете пересечения", e);
        }
        return result;
    }
}
