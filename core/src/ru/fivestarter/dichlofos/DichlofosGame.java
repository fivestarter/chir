package ru.fivestarter.dichlofos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.utils.Assets;
import ru.fivestarter.dichlofos.view.BattleScreen;
import ru.fivestarter.dichlofos.view.WorldScreen;

public class DichlofosGame extends Game {

    private Screen gameScreen;
    private Assets assets;

    @Override
    public void create() {
        assets = new Assets();
        gameScreen = new WorldScreen(this);
        ((WorldScreen) gameScreen).setTextureAtlas(getTextureAtlas());
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        assets.dispose();
    }

    private TextureAtlas getTextureAtlas() {
        return assets.getManager().get("atlas1.atlas", TextureAtlas.class);
    }

    public void changeOnBattleScreen() {
        setScreen(new BattleScreen());
    }
}
