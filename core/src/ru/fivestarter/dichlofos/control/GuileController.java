package ru.fivestarter.dichlofos.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.fivestarter.dichlofos.model.Character;

public class GuileController {

    private final Character character;

    public GuileController(Character character) {
        this.character = character;
    }

    public void handle(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            character.punch();
        }
    }
}
