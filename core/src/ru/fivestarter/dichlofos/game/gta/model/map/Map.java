package ru.fivestarter.dichlofos.game.gta.model.map;

import com.badlogic.gdx.graphics.g2d.Batch;
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
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;
import ru.fivestarter.dichlofos.utils.IntersectorUtil;

import java.util.*;
import java.util.stream.StreamSupport;

public class Map {
    private static final String Z_INDEX_LAYER_PREFIX = "Layer z-index";
    private static final String Z_INDEX_LAYER_MONOLITH_SUFFIX = "monolith";
    public static final String PORTAL_LAYER = "Порталы";
    public static final String GARAGE_LAYER = "Гараж";
    public static final String OBJECT_LAYER_1 = "Слой объектов 1";
    public static final String WORLD_MAP_FILE_NAME = "map/world.tmx";
    private final TiledMap tiledMap;
    private final List<List<TailSprite>> zIndexSpriteLayers;

    public Map() {
        this.tiledMap = new TmxMapLoader().load(WORLD_MAP_FILE_NAME);
        zIndexSpriteLayers = getSpritesFromZIndexedLayers(tiledMap);
    }

    private List<List<TailSprite>> getSpritesFromZIndexedLayers(TiledMap tiledMap) {
        return Arrays.stream(tiledMap.getLayers().getByType(TiledMapTileLayer.class).toArray(TiledMapTileLayer.class))
                .filter(layer -> StringUtils.startsWith(layer.getName(), Z_INDEX_LAYER_PREFIX))
                .sorted(Comparator.comparing(MapLayer::getName))
                .map(this::getGroupedSprites)
                .toList();
    }

    private List<TailSprite> getGroupedSprites(TiledMapTileLayer tiledMapTileLayer) {
        List<TailSprite> sprites = getSprites(tiledMapTileLayer);
        if (StringUtils.endsWith(tiledMapTileLayer.getName(), Z_INDEX_LAYER_MONOLITH_SUFFIX)) {
            sprites.stream()
                    .min(Comparator.comparing(TailSprite::getY))
                    .map(TailSprite::getY)
                    .ifPresent(minY -> sprites.forEach(tailSprite -> tailSprite.setMinY(minY)));
        }
        return sprites;
    }

    private List<TailSprite> getSprites(TiledMapTileLayer tiledMapTileLayer) {
        List<TailSprite> sprites = new ArrayList<>();
        for (int x = 0; x < tiledMapTileLayer.getWidth(); x++) {
            for (int y = 0; y < tiledMapTileLayer.getHeight(); y++) {
                int finalX = x;
                int finalY = y;
                Optional.ofNullable(tiledMapTileLayer.getCell(x, y))
                        .ifPresent(cell -> {
                            TextureRegion textureRegion = cell.getTile().getTextureRegion();
                            TailSprite sprite = new TailSprite(textureRegion, finalX, finalY);
                            sprites.add(sprite);
                        });
            }
        }
        return sprites;
    }

    public void drawTopZIndexTileLayer(Batch batch, float heroY) {
        zIndexSpriteLayers.stream()
                .flatMap(List::stream)
                .filter(sprite -> sprite.getMinY() < heroY)
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
        rectangle.setX(rectangle.getX() / WorldScreen.UNIT_SCALE);
        rectangle.setY(rectangle.getY() / WorldScreen.UNIT_SCALE);
        rectangle.setWidth(rectangle.getWidth() / WorldScreen.UNIT_SCALE);
        rectangle.setHeight(rectangle.getHeight() / WorldScreen.UNIT_SCALE);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void dispose() {
        tiledMap.dispose();
    }

    public boolean isGarageOverlapped(Rectangle rectangle) {
        unscaleCoordinates(rectangle);
        return StreamSupport.stream(tiledMap.getLayers().get(GARAGE_LAYER).getObjects().spliterator(), true)
                .anyMatch(mapObject -> ((RectangleMapObject) mapObject).getRectangle().overlaps(rectangle));
    }
}
