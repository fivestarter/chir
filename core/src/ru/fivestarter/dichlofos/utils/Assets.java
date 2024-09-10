package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private static final String GTA_ATLAS_FILE_NAME = "atlas/gta/gta.atlas";
    private static final String FIGHT_ATLAS_FILE_NAME = "atlas/fight/fight.atlas";
    private final AssetManager manager;

    public Assets() {
        this.manager = new AssetManager();
        manager.load(GTA_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.load(FIGHT_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.finishLoading();
    }

    public TextureAtlas getGtaTextureAtlas() {
        return manager.get(GTA_ATLAS_FILE_NAME, TextureAtlas.class);
    }

    public TextureAtlas getFightTextureAtlas() {
        return manager.get(FIGHT_ATLAS_FILE_NAME, TextureAtlas.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
