package ru.fivestarter.dichlofos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.game.fight.FightScreen;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;
import ru.fivestarter.dichlofos.game.jrpg.view.BattleScreen;
import ru.fivestarter.dichlofos.menu.view.MenuScreen;
import ru.fivestarter.dichlofos.utils.Assets;

import static ru.fivestarter.dichlofos.utils.Assets.COMMON_ATLAS_FILE_NAME;

public class DichlofosGame extends Game implements ScreenChanger {

    private Screen gameScreen;
    private Assets assets;

    @Override
    public void create() {
        assets = new Assets();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        assets.dispose();
    }

    private TextureAtlas getTextureAtlas() {
        return assets.getManager().get(COMMON_ATLAS_FILE_NAME, TextureAtlas.class);
    }

    @Override
    public void changeOnBattleScreen() {
        setScreen(new BattleScreen(getTextureAtlas()));
    }

    @Override
    public void changeOnWorldScreen() {
        gameScreen = new WorldScreen(this, assets.getManager());
        setScreen(gameScreen);
    }

    @Override
    public void changeOnFightScreen() {
        setScreen(new FightScreen());
    }

}
