package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.kick.LeftHighKick;
import ru.fivestarter.dichlofos.game.fight.model.kick.RightHighKick;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {

    private final KickController rightHighKickController;
    private final KickController leftHighKickController;

    public FightController(Assets assets) {
        this.rightHighKickController = new KickController(new RightHighKick(assets, new Rectangle(520, 380, 34, 75)));
        this.leftHighKickController = new KickController(new LeftHighKick(assets, new Rectangle(530, 330, 34, 75)));
    }

    public void draw(float delta, SpriteBatch batch) {
        rightHighKickController.draw(delta, batch);
        leftHighKickController.draw(delta, batch);
        handle();
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET) && !rightHighKickController.isVisible()) {
            rightHighKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P) && !leftHighKickController.isVisible()) {
            leftHighKickController.kick();
        }
    }
}
