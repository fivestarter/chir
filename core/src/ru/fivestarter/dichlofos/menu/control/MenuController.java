package ru.fivestarter.dichlofos.menu.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.fivestarter.dichlofos.ScreenChanger;

public class MenuController {
    private final ScreenChanger screenChanger;

    public MenuController(ScreenChanger screenChanger) {
        this.screenChanger = screenChanger;
    }

    public void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            screenChanger.changeOnWorldScreen();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            screenChanger.changeOnWorldScreen();
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            screenChanger.changeOnFightScreen();
        }
    }
}
