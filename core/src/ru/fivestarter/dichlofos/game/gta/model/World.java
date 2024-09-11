package ru.fivestarter.dichlofos.game.gta.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.gta.control.CarController;
import ru.fivestarter.dichlofos.game.gta.control.CharacterController;
import ru.fivestarter.dichlofos.game.gta.control.HeroController;
import ru.fivestarter.dichlofos.game.gta.model.map.Map;
import ru.fivestarter.dichlofos.game.gta.view.Operator;
import ru.fivestarter.dichlofos.utils.Assets;

public class World {
    private final Map map;
    private CharacterController mainHeroController;
    private final Runnable portalConsumer;
    private final Assets assets;
    private final Operator operator;

    public World(Assets assets, Runnable portalConsumer, Operator operator) {
        this.assets = assets;
        this.operator = operator;
        this.map = new Map();
        this.portalConsumer = portalConsumer;
        this.mainHeroController = new HeroController(assets, this);
    }

    public boolean isObstacle(Polygon polygon) {
        return map.isBorderOverlapped(polygon);
    }

    public boolean isObstacle(Rectangle rectangle) {
        return map.isBorderOverlapped(rectangle);
    }

    public void draw(float delta, Batch batch) {
        mainHeroController.draw(delta, batch);
        map.drawTopZIndexTileLayer(batch, mainHeroController.getModel().getY());
        handlePortal(mainHeroController.getModel().getBoundingRectangle());
    }

    private void handlePortal(Rectangle rectangle) {
        if (map.isPortalOverlapped(rectangle)) {
            portalConsumer.run();
            dispose();
        }
    }

    public void handleGarage(Rectangle rectangle) {
        if (map.isGarageOverlapped(rectangle)) {
            //убрать возможные зацикливания
            this.mainHeroController = new CarController(assets.findCar(),
                    this,
                    rectangle.getX(),
                    rectangle.getY());
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
