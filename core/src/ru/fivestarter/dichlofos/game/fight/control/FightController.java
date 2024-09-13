package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.kick.LeftHighKick;
import ru.fivestarter.dichlofos.game.fight.model.kick.RightHighKick;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {

    private final RightHighKick kick;
    private final LeftHighKick leftHighKick;

    public FightController(Assets assets) {
        this.kick = new RightHighKick(assets, new Rectangle(520, 380, 34, 75));
        leftHighKick = new LeftHighKick(assets, new Rectangle(530, 330, 34, 75));
    }

    public void draw(float delta, SpriteBatch batch) {
        kick.draw(delta, batch);
        leftHighKick.draw(delta, batch);
        handle();
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET) && !kick.isVisible()) {
            kick.start();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P) && !leftHighKick.isVisible()) {
            leftHighKick.start();
        }
    }
}
