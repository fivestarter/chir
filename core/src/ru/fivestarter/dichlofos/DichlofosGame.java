package ru.fivestarter.dichlofos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.game.fight.FightScreen;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;
import ru.fivestarter.dichlofos.menu.view.MenuScreen;
import ru.fivestarter.dichlofos.utils.Assets;

import static ru.fivestarter.dichlofos.utils.Assets.FIGHT_ATLAS_FILE_NAME;
import static ru.fivestarter.dichlofos.utils.Assets.GTA_HERO_ATLAS_FILE_NAME;

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
        fightScreen = new FightScreen(assets.getManager().get(FIGHT_ATLAS_FILE_NAME, TextureAtlas.class));
        setScreen(fightScreen);
    }

    @Override
    public void changeOnWorldScreen() {
        gameScreen = new WorldScreen(this, assets.getManager().get(GTA_HERO_ATLAS_FILE_NAME, TextureAtlas.class));
        setScreen(gameScreen);
    }

}
