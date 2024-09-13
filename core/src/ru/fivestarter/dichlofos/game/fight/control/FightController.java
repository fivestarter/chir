package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.kick.RightHighKick;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {

    private static final Rectangle KICK_FRAME = new Rectangle(520, 380, 34, 75);
    private final RightHighKick kick;

    public FightController(Assets assets) {
        this.kick = new RightHighKick(assets, KICK_FRAME);
    }

    public void draw(float delta, SpriteBatch batch) {
        kick.draw(delta, batch);
        handle();
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET) && !kick.isVisible()) {
            kick.start();
        }
    }
}
