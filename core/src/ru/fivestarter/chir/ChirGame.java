package ru.fivestarter.chir;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.chir.utils.Assets;
import ru.fivestarter.chir.view.BattleScreen;
import ru.fivestarter.chir.view.WorldScreen;

public class ChirGame extends Game {

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
