package ru.fivestarter.dichlofos.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager manager;

    public Assets() {
        this.manager = new AssetManager();
        manager.load("atlas1.atlas", TextureAtlas.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose() {

    }
}
