package ru.fivestarter.chir;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ru.fivestarter.chir.view.GameScreen;

public class ChirGame extends Game {

    private Screen gameScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
