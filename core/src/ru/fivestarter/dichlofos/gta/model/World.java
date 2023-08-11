package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.human.Hero;
import ru.fivestarter.dichlofos.gta.model.map.Map;

import static ru.fivestarter.dichlofos.utils.Assets.GTA_HERO_ATLAS_FILE_NAME;

public class World {
    private final Map map;
    private final Sprite mainHero;
    private final Runnable portalConsumer;

    public World(AssetManager assetManager, Runnable portalConsumer) {
        this.map = new Map();
        //this.mainHero = new Mercedes(assetManager.get(COMMON_ATLAS_FILE_NAME, TextureAtlas.class).findRegion(SPRITE_NAME), this, 40, 49);
        this.mainHero = new Hero(assetManager.get(GTA_HERO_ATLAS_FILE_NAME, TextureAtlas.class).findRegion(Hero.SPRITE_NAME), 40, 49, this);
        this.portalConsumer = portalConsumer;
    }

    public boolean isObstacle(Polygon polygon) {
        return map.isBorderOverlapped(polygon);
    }

    public boolean isObstacle(Rectangle rectangle) {
        return map.isBorderOverlapped(rectangle);
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
