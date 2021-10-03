package ru.fivestarter.dichlofos.jrpg.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.fivestarter.dichlofos.jrpg.model.Character;

public class GuileController {

    private final Character character;

    public GuileController(Character character) {
        this.character = character;
    }

    public void handle(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            character.punch();
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            character.highKick();
        }
    }
}
