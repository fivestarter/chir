package ru.fivestarter.dichlofos.gta.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import ru.fivestarter.dichlofos.ScreenChanger;
import ru.fivestarter.dichlofos.gta.model.World;

public class WorldScreen implements Screen {
    public static float DELTA_CFF;
    public static final float UNIT_SCALE = 1f / 16f;

    private final ScreenChanger screenChanger;

    private final AssetManager assetManager;
    private Batch batch;
    private World world;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private int viewportWidth = 10;
    private int viewportHeight = 6;

    public WorldScreen(ScreenChanger screenChanger, AssetManager assetManager) {
        this.screenChanger = screenChanger;
        this.assetManager = assetManager;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        world = new World(assetManager, screenChanger::changeOnBattleScreen, this);
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

/*    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        camera.setToOrtho(false, 40, 24);
    }*/

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        camera.setToOrtho(false, viewportWidth, viewportHeight);
    }

    public void setBigCamera() {
        viewportWidth = 40;
        viewportHeight = 24;
        camera.setToOrtho(false, viewportWidth, viewportHeight);
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
