package ru.fivestarter.dichlofos.gta.model.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import org.apache.commons.lang3.StringUtils;
import ru.fivestarter.dichlofos.utils.IntersectorUtil;

import java.util.*;
import java.util.stream.StreamSupport;

import static ru.fivestarter.dichlofos.gta.view.WorldScreen.UNIT_SCALE;

public class Map {
    private static final String Z_INDEX_LAYER_PREFIX = "Layer z-index";
    public static final String PORTAL_LAYER = "Порталы";
    public static final String OBJECT_LAYER_1 = "Слой объектов 1";
    public static final String WORLD_MAP_FILE_NAME = "map/world.tmx";
    private final TiledMap tiledMap;
    private final List<List<Sprite>> zIndexSpriteLayers;

    public Map() {
        this.tiledMap = new TmxMapLoader().load(WORLD_MAP_FILE_NAME);
        zIndexSpriteLayers = getSpritesFromZIndexedLayers(tiledMap);
    }

    private List<List<Sprite>> getSpritesFromZIndexedLayers(TiledMap tiledMap) {
        return Arrays.stream(tiledMap.getLayers().getByType(TiledMapTileLayer.class).toArray(TiledMapTileLayer.class))
                .filter(layer -> StringUtils.startsWith(layer.getName(), Z_INDEX_LAYER_PREFIX))
                .sorted(Comparator.comparing(MapLayer::getName))
                .map(this::getSprites)
                .toList();
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
        return StreamSupport.stream(tiledMap.getLayers().get(OBJECT_LAYER_1).getObjects().spliterator(), true)
                .anyMatch(mapObject -> {
                    Rectangle borederRectangle = ((RectangleMapObject) mapObject).getRectangle();
                    return IntersectorUtil.overlapConvexPolygons(polygon, borederRectangle);
                });
    }

    public boolean isBorderOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(tiledMap.getLayers().get(OBJECT_LAYER_1).getObjects().spliterator(), true)
                .anyMatch(mapObject -> {
                    Rectangle borederRectangle = ((RectangleMapObject) mapObject).getRectangle();
                    return Intersector.overlaps(rectangle, borederRectangle);
                });
    }

    public boolean isPortalOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(tiledMap.getLayers().get(PORTAL_LAYER).getObjects().spliterator(), true)
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
