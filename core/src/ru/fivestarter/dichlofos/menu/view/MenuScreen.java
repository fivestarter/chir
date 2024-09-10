package ru.fivestarter.dichlofos.menu.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fivestarter.dichlofos.ScreenChanger;
import ru.fivestarter.dichlofos.menu.control.MenuController;

public class MenuScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont textFont;

    private final MenuController menuController;

    public MenuScreen(ScreenChanger screenChanger) {
        this.menuController = new MenuController(screenChanger);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();

        textFont = new BitmapFont();
        textFont.setColor(Color.CORAL);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5294118F, 0.80784315F, 0.92156863F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        textFont.draw(batch, "Press A-button to use the human", 300, 260);
        textFont.draw(batch, "Press D-button to fight", 300, 230);
        menuController.handle();
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

    }
}
