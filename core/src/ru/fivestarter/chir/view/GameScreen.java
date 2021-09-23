package ru.fivestarter.chir.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import ru.fivestarter.chir.model.Car;
import ru.fivestarter.chir.model.Mercedes;

public class GameScreen implements Screen {
    public static float DELTA_CFF;
    public static final float UNIT_SCALE = 1f / 16f;

    private TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private Car car;
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void show() {
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("map/chir.tmx");
        car = new Mercedes(textureAtlas.findRegion("car"), map, 10, 10);
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        DELTA_CFF = delta;

        renderCamera();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        car.draw(batch);
        batch.end();
    }

    private void renderCamera() {
        camera.translate(0.0f, 0.02f);
        camera.position.x = car.getBounds().getX();
        camera.position.y = car.getBounds().getY();
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        camera.setToOrtho(false, 40, 24);
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
        map.dispose();
        renderer.dispose();
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }
}
