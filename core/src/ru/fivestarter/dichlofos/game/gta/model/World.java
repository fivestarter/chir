package ru.fivestarter.dichlofos.game.gta.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.gta.control.CarController;
import ru.fivestarter.dichlofos.game.common.CharacterController;
import ru.fivestarter.dichlofos.game.gta.control.HeroController;
import ru.fivestarter.dichlofos.game.gta.model.map.Map;
import ru.fivestarter.dichlofos.game.gta.view.Operator;

public class World {
    private final Map map;
    private CharacterController mainHeroController;
    private final Runnable portalConsumer;
    private final AssetManager assetManager;
    private final Operator operator;

    public World(AssetManager assetManager, Runnable portalConsumer, Operator operator) {
        this.operator = operator;
        this.map = new Map();
        this.assetManager = assetManager;
        this.portalConsumer = portalConsumer;
        this.mainHeroController = new HeroController(assetManager, this);
    }

    public boolean isObstacle(Polygon polygon) {
        return map.isBorderOverlapped(polygon);
    }

    public boolean isObstacle(Rectangle rectangle) {
        return map.isBorderOverlapped(rectangle);
    }

    public void draw(Batch batch) {
        mainHeroController.draw(batch);
        map.drawTopZIndexTileLayer(batch, mainHeroController.getModel().getY());
        handlePortal(mainHeroController.getModel().getBoundingRectangle());
        handleGarage(mainHeroController.getModel().getBoundingRectangle());
    }

    private void handlePortal(Rectangle rectangle) {
        if (map.isPortalOverlapped(rectangle)) {
            portalConsumer.run();
            dispose();
        }
    }

    private void handleGarage(Rectangle rectangle) {
        if (map.isGarageOverlapped(rectangle)) {
            //убрать возможные зацикливания
            this.mainHeroController = new CarController(assetManager, this);
            operator.setBigCamera();
        }
    }

    public TiledMap getTiledMap() {
        return map.getTiledMap();
    }

    public float getCameraPositionX() {
        return mainHeroController.getModel().getX();
    }

    public float getCameraPositionY() {
        return mainHeroController.getModel().getY();
    }

    public void dispose() {
        map.dispose();
    }
}
