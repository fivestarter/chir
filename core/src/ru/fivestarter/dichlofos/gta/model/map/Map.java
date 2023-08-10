package ru.fivestarter.dichlofos.gta.model.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.utils.IntersectorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Map {
    private final TiledMap tiledMap;
    private final List<List<Sprite>> zIndexSpriteLayers = new ArrayList<>();

    public Map() {
        this.tiledMap = new TmxMapLoader().load("map/world.tmx");
        zIndexSpriteLayers.add(getSprites(((TiledMapTileLayer) tiledMap.getLayers().get("Layer z-index 1"))));
        zIndexSpriteLayers.add(getSprites(((TiledMapTileLayer) tiledMap.getLayers().get("Layer z-index 2"))));
    }

    private List<Sprite> getSprites(TiledMapTileLayer tiledMapTileLayer) {
        List<Sprite> sprites = new ArrayList<>();
        for (int x = 0; x < tiledMapTileLayer.getWidth(); x++) {
            for (int y = 0; y < tiledMapTileLayer.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                Optional.ofNullable(tiledMapTileLayer.getCell(x, y))
                        .ifPresent(cell -> {
                            TextureRegion textureRegion = cell.getTile().getTextureRegion();
                            Sprite sprite = new TailSprite(textureRegion, finalX, finalY);
                            sprites.add(sprite);
                        });
            }
        }
        return sprites;
    }

    public void drawTopZIndexTileLayer(Batch batch) {
        zIndexSpriteLayers.stream()
                .flatMap(List::stream)
                .forEach(sprite -> sprite.draw(batch));
    }

    public boolean isBorderOverlapped(Polygon polygon) {
        return StreamSupport.stream(tiledMap.getLayers().get("Слой объектов 1").getObjects().spliterator(), true)
                .anyMatch(mapObject -> {
                    Rectangle borederRectangle = ((RectangleMapObject) mapObject).getRectangle();
                    return IntersectorUtil.overlapConvexPolygons(polygon, borederRectangle);
                });
    }

    public boolean isPortalOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(tiledMap.getLayers().get("Порталы").getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().overlaps(rectangle));
    }

    private void unscaleCoordinates(Rectangle rectangle) {
        rectangle.setX(rectangle.getX() / UNIT_SCALE);
        rectangle.setY(rectangle.getY() / UNIT_SCALE);
        rectangle.setWidth(rectangle.getWidth() / UNIT_SCALE);
        rectangle.setHeight(rectangle.getHeight() / UNIT_SCALE);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void dispose() {
        tiledMap.dispose();
    }
}
