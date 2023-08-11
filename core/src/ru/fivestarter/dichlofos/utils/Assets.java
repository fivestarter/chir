package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public static final String COMMON_ATLAS_FILE_NAME = "atlas/common/atlas1.atlas";
    private final AssetManager manager;

    public Assets() {
        this.manager = new AssetManager();
        manager.load(COMMON_ATLAS_FILE_NAME, TextureAtlas.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {

    }
}
