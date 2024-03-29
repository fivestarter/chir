package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public static final String COMMON_ATLAS_FILE_NAME = "atlas/common/atlas1.atlas";
    public static final String GTA_HERO_ATLAS_FILE_NAME = "atlas/gta/hero/hero.atlas";
    private final AssetManager manager;

    public Assets() {
        this.manager = new AssetManager();
        manager.load(COMMON_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.load(GTA_HERO_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {
        manager.dispose();
    }
}
