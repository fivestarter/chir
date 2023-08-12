package ru.fivestarter.dichlofos.game.jrpg.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.game.common.CharacterController;
import ru.fivestarter.dichlofos.game.jrpg.model.Character;
import ru.fivestarter.dichlofos.game.jrpg.model.guile.Guile;

public class GuileController implements CharacterController<Character> {

    private final Guile guile;

    public GuileController(TextureAtlas textureAtlas) {
        this.guile = new Guile(textureAtlas, 130, 60, 430 - 160);
    }

    @Override
    public Character getModel() {
        return guile;
    }

    @Override
    public void draw(Batch batch) {
        handle();
        guile.draw(batch);
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            guile.punch();
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            guile.highKick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            guile.move();
        }
    }
}
