package ru.fivestarter.chir.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import java.util.stream.StreamSupport;

import static ru.fivestarter.chir.view.GameScreen.UNIT_SCALE;

public class World {
    private final Car car;
    private final TiledMap map;

    public World(TextureAtlas textureAtlas) {
        this.map = new TmxMapLoader().load("map/world.tmx");
        this.car = new Mercedes(textureAtlas.findRegion("car"), this, 10, 10);
    }

    public boolean isBorderOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(map.getLayers().get("Слой объектов 1").getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().overlaps(rectangle));
    }

    private void unscaleCoordinates(Rectangle rectangle) {
        rectangle.setX(rectangle.getX()/ UNIT_SCALE);
        rectangle.setY(rectangle.getY()/ UNIT_SCALE);
        rectangle.setWidth(rectangle.getWidth()/ UNIT_SCALE);
        rectangle.setHeight(rectangle.getHeight()/ UNIT_SCALE);
    }

    public void draw(SpriteBatch batch) {
        car.draw(batch);
    }

    public TiledMap getMap() {
        return map;
    }

    public float getCameraPositionX() {
        return car.getBounds().getX();
    }

    public float getCameraPositionY() {
        return car.getBounds().getY();
    }

    public void dispose() {
        map.dispose();
    }
}