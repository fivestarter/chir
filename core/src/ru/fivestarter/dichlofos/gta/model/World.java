package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.Car;
import ru.fivestarter.dichlofos.gta.model.character.Mercedes;
import ru.fivestarter.dichlofos.gta.model.map.TailSprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static ru.fivestarter.dichlofos.gta.model.character.Mercedes.SPRITE_NAME;
import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class World {
    private final Car car;
    private final TiledMap map;
    private final List<TiledMapTileLayer> zIndexLayers = new ArrayList<>();
    private final Runnable portalConsumer;

    public World(TextureAtlas textureAtlas, Runnable portalConsumer) {
        this.portalConsumer = portalConsumer;
        this.map = new TmxMapLoader().load("map/world.tmx");
        this.zIndexLayers.add(((TiledMapTileLayer) map.getLayers().get("Layer z-index 1")));
        this.zIndexLayers.add(((TiledMapTileLayer) map.getLayers().get("Layer z-index 2")));
        this.car = new Mercedes(textureAtlas.findRegion(SPRITE_NAME), this, 40, 49);
    }

    public boolean isBorderOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(map.getLayers().get("Слой объектов 1").getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().overlaps(rectangle));
    }

    public boolean isPortalOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(map.getLayers().get("Порталы").getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().overlaps(rectangle));
    }

    private void unscaleCoordinates(Rectangle rectangle) {
        rectangle.setX(rectangle.getX() / UNIT_SCALE);
        rectangle.setY(rectangle.getY() / UNIT_SCALE);
        rectangle.setWidth(rectangle.getWidth() / UNIT_SCALE);
        rectangle.setHeight(rectangle.getHeight() / UNIT_SCALE);
    }

    public void draw(Batch batch) {
        car.draw(batch);
        drawTopZIndexTileLayer(batch);
        handlePortal(car.getBoundingRectangle());
    }

    private void drawTopZIndexTileLayer(Batch batch) {
        zIndexLayers.forEach(tiledMapTileLayer -> {
            for (int x = 0; x < tiledMapTileLayer.getWidth(); x++) {
                for (int y = 0; y < tiledMapTileLayer.getHeight(); y++) {
                    int finalX = x;
                    int finalY = y;
                    Optional.ofNullable(tiledMapTileLayer.getCell(x, y))
                            .ifPresent(cell -> {
                                TextureRegion textureRegion = cell.getTile().getTextureRegion();
                                Sprite sprite = new TailSprite(textureRegion, finalX, finalY);
                                sprite.draw(batch);
                            });
                }
            }
        });
    }

    private void handlePortal(Rectangle rectangle) {
        if (isPortalOverlapped(rectangle)) {
            portalConsumer.run();
            dispose();
        }
    }

    public TiledMap getMap() {
        return map;
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
