package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.Car;
import ru.fivestarter.dichlofos.gta.model.character.Mercedes;
import ru.fivestarter.dichlofos.gta.model.map.Map;

import static ru.fivestarter.dichlofos.gta.model.character.Mercedes.SPRITE_NAME;

public class World {
    private final Map map;
    private final Car car;
    private final Runnable portalConsumer;

    public World(TextureAtlas textureAtlas, Runnable portalConsumer) {
        this.map = new Map();
        this.car = new Mercedes(textureAtlas.findRegion(SPRITE_NAME), this, 40, 49);
        this.portalConsumer = portalConsumer;
    }

    public boolean isObstacle(Rectangle rectangle) {
        return map.isBorderOverlapped(rectangle);
    }

    public void draw(Batch batch) {
        car.draw(batch);
        map.drawTopZIndexTileLayer(batch);
        handlePortal(car.getBoundingRectangle());
    }

    private void handlePortal(Rectangle rectangle) {
        if (map.isPortalOverlapped(rectangle)) {
            portalConsumer.run();
            dispose();
        }
    }

    public TiledMap getTiledMap() {
        return map.getTiledMap();
    }

    public float getCameraPositionX() {
        return car.getX();
    }

    public float getCameraPositionY() {
        return car.getY();
    }

    public void dispose() {
        map.dispose();
    }
}
