package ru.fivestarter.dichlofos.game.jrpg.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.fivestarter.dichlofos.game.common.CharacterController;
import ru.fivestarter.dichlofos.game.jrpg.control.GuileController;
import ru.fivestarter.dichlofos.game.jrpg.model.Character;
import ru.fivestarter.dichlofos.game.jrpg.model.honda.Honda;

public class BattleScreen implements Screen {
    private Viewport viewport;

    private final TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private TextureRegion background;
    private CharacterController<Character> mainHeroController;
    private Honda enemy;

    public BattleScreen(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(40, 24, camera);
        batch = new SpriteBatch();
        background = textureAtlas.findRegion("pun");
        mainHeroController = new GuileController(textureAtlas);
        enemy = new Honda(textureAtlas, 430, 60);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();

        batch.begin();
        batch.draw(background, 0, 0);
        mainHeroController.draw(delta, batch);
        enemy.draw(batch);
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
