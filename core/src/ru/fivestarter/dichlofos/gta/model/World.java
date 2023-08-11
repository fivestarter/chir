package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.car.Mercedes;
import ru.fivestarter.dichlofos.gta.model.map.Map;

import static ru.fivestarter.dichlofos.gta.model.character.car.Mercedes.SPRITE_NAME;

public class World {
    private final Map map;
    private final Sprite mainHero;
    private final Runnable portalConsumer;

    public World(TextureAtlas textureAtlas, Runnable portalConsumer) {
        this.map = new Map();
        this.mainHero = new Mercedes(textureAtlas.findRegion(SPRITE_NAME), this, 40, 49);
        this.portalConsumer = portalConsumer;
    }

    public boolean isObstacle(Polygon polygon) {
        return map.isBorderOverlapped(polygon);
    }

    public void draw(Batch batch) {
        mainHero.draw(batch);
        map.drawTopZIndexTileLayer(batch);
        handlePortal(mainHero.getBoundingRectangle());
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
        return mainHero.getX();
    }

    public float getCameraPositionY() {
        return mainHero.getY();
    }

    public void dispose() {
        map.dispose();
    }
}
