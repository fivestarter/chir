package ru.fivestarter.dichlofos.game.jrpg.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.fivestarter.dichlofos.game.jrpg.model.guile.Guile;
import ru.fivestarter.dichlofos.game.jrpg.model.honda.Honda;

public class BattleScreen implements Screen {

    private final TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureRegion background;
    private Guile mainHero;
    private Honda enemy;

    public BattleScreen(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        background = textureAtlas.findRegion("pun");
        mainHero = new Guile(textureAtlas, 130, 60, 430 - 160);
        enemy = new Honda(textureAtlas, 430, 60);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainHero.draw(batch);
        enemy.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        font.dispose();
    }
}
