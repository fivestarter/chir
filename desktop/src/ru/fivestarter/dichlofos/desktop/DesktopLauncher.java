package ru.fivestarter.dichlofos.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fivestarter.dichlofos.DichlofosGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Дихлофос";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new DichlofosGame(), config);
	}
}
