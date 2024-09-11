package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    private static final String GTA_ATLAS_FILE_NAME = "atlas/gta/gta.atlas";
    private static final String FIGHT_ATLAS_FILE_NAME = "atlas/fight/fight.atlas";
    public static final String ENEMY = "enemy";
    public static final String HERO = "hero";
    public static final String CAR = "car";
    public static final String MH_WALK = "mh_walk";
    public static final String MH_RUN = "mh_run";
    public static final String FOOTPRINT = "footprint";
    private final AssetManager manager;

    public Assets() {
        this.manager = new AssetManager();
        manager.load(GTA_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.load(FIGHT_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.finishLoading();
    }

    public TextureRegion findEnemy() {
        return getRegion(FIGHT_ATLAS_FILE_NAME, ENEMY);
    }

    public TextureRegion findHero() {
        return getRegion(FIGHT_ATLAS_FILE_NAME, HERO);
    }

    public TextureRegion findFootPrint() {
        return getRegion(FIGHT_ATLAS_FILE_NAME, FOOTPRINT);
    }

    public TextureRegion findCar() {
        return getRegion(GTA_ATLAS_FILE_NAME, CAR);
    }

    public TextureRegion findMainHeroWalk() {
        return getRegion(GTA_ATLAS_FILE_NAME, MH_WALK);
    }

    public TextureRegion findMainHeroRun() {
        return getRegion(GTA_ATLAS_FILE_NAME, MH_RUN);
    }

    private TextureAtlas.AtlasRegion getRegion(String atlasFileName, String regionName) {
        return manager.get(atlasFileName, TextureAtlas.class).findRegion(regionName);
    }

    public void dispose() {
        manager.dispose();
    }
}
