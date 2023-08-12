package ru.fivestarter.dichlofos.gta.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.gta.model.character.car.Mercedes;
import ru.fivestarter.dichlofos.gta.model.character.human.Hero;
import ru.fivestarter.dichlofos.gta.model.map.Map;
import ru.fivestarter.dichlofos.gta.view.Operator;

import static ru.fivestarter.dichlofos.gta.model.character.car.Mercedes.SPRITE_NAME;
import static ru.fivestarter.dichlofos.utils.Assets.COMMON_ATLAS_FILE_NAME;
import static ru.fivestarter.dichlofos.utils.Assets.GTA_HERO_ATLAS_FILE_NAME;

public class World {
    private final Map map;
    private Sprite mainHero;
    private final Runnable portalConsumer;
    private final AssetManager assetManager;
    private final Operator operator;

    public World(AssetManager assetManager, Runnable portalConsumer, Operator operator) {
        this.operator = operator;
        this.map = new Map();
        this.assetManager = assetManager;
        TextureAtlas.AtlasRegion region = this.assetManager.get(GTA_HERO_ATLAS_FILE_NAME, TextureAtlas.class)
                .findRegion(Hero.SPRITE_NAME);
        this.mainHero = new Hero(region, 40, 49, this);
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
        map.drawTopZIndexTileLayer(batch, mainHero.getY());
        handlePortal(mainHero.getBoundingRectangle());
        handleGarage(mainHero.getBoundingRectangle());
    }

    private void handlePortal(Rectangle rectangle) {
        if (map.isPortalOverlapped(rectangle)) {
            portalConsumer.run();
            dispose();
        }
    }

    private void handleGarage(Rectangle rectangle) {
        if (map.isGarageOverlapped(rectangle)) {
            TextureAtlas.AtlasRegion region = assetManager.get(COMMON_ATLAS_FILE_NAME, TextureAtlas.class)
                    .findRegion(SPRITE_NAME);
            //убрать возможные зацикливания
            this.mainHero = new Mercedes(region, this, 40, 49);
            operator.setBigCamera();
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
