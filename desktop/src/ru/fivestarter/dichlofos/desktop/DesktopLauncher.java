package ru.fivestarter.dichlofos.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.fivestarter.dichlofos.DichlofosGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Дихлофос");
        config.setWindowedMode(800, 480);
        new Lwjgl3Application(new DichlofosGame(), config);
    }
}
