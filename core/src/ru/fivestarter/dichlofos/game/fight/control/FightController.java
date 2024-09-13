package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.kick.RightHighKick;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {

    private static final Rectangle KICK_FRAME = new Rectangle(520, 380, 34, 75);
    private RightHighKick kick;
    private final Assets assets;
    private float rightHighKickTTL = 0;

    public FightController(Assets assets) {
        this.kick = new RightHighKick(assets, KICK_FRAME);
        this.assets = assets;
    }

    public void draw(float delta, SpriteBatch batch) {
        if (rightHighKickTTL > 0) {
            kick.draw(delta, batch);
            rightHighKickTTL -= delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET) && rightHighKickTTL <= 0) {
            rightHighKickTTL = 1;
            kick = new RightHighKick(assets, KICK_FRAME);
        }
    }
}
