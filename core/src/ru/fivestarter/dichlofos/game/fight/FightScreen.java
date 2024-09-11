package ru.fivestarter.dichlofos.game.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightScreen implements Screen {
    private static final Rectangle ENEMY_FRAME = new Rectangle(500, 200, 122, 250);
    private static final Rectangle HERO_FRAME = new Rectangle(130, 80, 228, 356);
    private final Assets assets;
    private Viewport viewport;

    private SpriteBatch batch;
    private Sprite enemy;
    private Sprite hero;
    private FightController fightController;
    public FightScreen(Assets assets) {
        this.assets = assets;
    }


    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(40, 24, camera);
        batch = new SpriteBatch();
        enemy = new Fighter(assets.findEnemy(), ENEMY_FRAME);
        hero = new Fighter(assets.findHero(), HERO_FRAME);
        fightController = new FightController(assets);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();

        batch.begin();
        enemy.draw(batch);
        hero.draw(batch);
        fightController.draw(delta, batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    }
}
