package ru.fivestarter.dichlofos.gta.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.fivestarter.dichlofos.ScreenChanger;
import ru.fivestarter.dichlofos.gta.model.World;

public class WorldScreen implements Screen, Operator {
    public static float DELTA_CFF;
    public static final float UNIT_SCALE = 1f / 16f;
    private static final int VIEWPORT_SMALL_WIDTH = 10;
    private static final int VIEWPORT_SMALL_HEIGHT = 6;
    private static final int VIEWPORT_BIG_WIDTH = 40;
    private static final int VIEWPORT_BIG_HEIGHT = 24;

    private final ScreenChanger screenChanger;

    private final AssetManager assetManager;
    private Batch batch;
    private World world;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    private Viewport viewport;

    public WorldScreen(ScreenChanger screenChanger, AssetManager assetManager) {
        this.screenChanger = screenChanger;
        this.assetManager = assetManager;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        world = new World(assetManager, screenChanger::changeOnBattleScreen, this);
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIEWPORT_SMALL_WIDTH, VIEWPORT_SMALL_HEIGHT, camera);
        renderer = new OrthogonalTiledMapRenderer(world.getTiledMap(), UNIT_SCALE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        DELTA_CFF = delta;

        renderCamera();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.draw(batch);
        batch.end();
    }

    private void renderCamera() {
        camera.translate(0.0f, 0.02f);
        camera.position.x = world.getCameraPositionX();
        camera.position.y = world.getCameraPositionY();
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void setBigCamera() {
        viewport.setWorldSize(VIEWPORT_BIG_WIDTH, VIEWPORT_BIG_HEIGHT);
        viewport.apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        renderer.dispose();
    }
}
