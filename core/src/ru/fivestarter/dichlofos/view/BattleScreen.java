package ru.fivestarter.dichlofos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.model.GameObject;
import ru.fivestarter.dichlofos.model.Guile;

public class BattleScreen implements Screen {

    private final TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background;
    private GameObject mainHero;

    public BattleScreen(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        background = new Texture(Gdx.files.internal("scene/pun.png"));
        TextureAtlas.AtlasRegion guileRegion = textureAtlas.findRegion("guile");
        mainHero = new Guile(guileRegion.split(guileRegion.getRegionWidth()/25, 107)[0][0], 200, 100, 170, 400);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainHero.draw(batch);
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
