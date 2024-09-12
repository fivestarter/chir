package ru.fivestarter.dichlofos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ru.fivestarter.dichlofos.game.fight.view.FightScreen;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;
import ru.fivestarter.dichlofos.menu.view.MenuScreen;
import ru.fivestarter.dichlofos.utils.Assets;

public class DichlofosGame extends Game implements ScreenChanger {

    private Screen gameScreen;
    private Assets assets;
    private FightScreen fightScreen;

    @Override
    public void create() {
        assets = new Assets();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        fightScreen.dispose();
        assets.dispose();
    }

    @Override
    public void changeOnBattleScreen() {
        fightScreen = new FightScreen(assets);
        setScreen(fightScreen);
    }

    @Override
    public void changeOnWorldScreen() {
        gameScreen = new WorldScreen(this, assets);
        setScreen(gameScreen);
    }

}
