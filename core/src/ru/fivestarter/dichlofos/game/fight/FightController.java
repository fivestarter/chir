package ru.fivestarter.dichlofos.game.fight;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {
    private static final Rectangle KICK_FRAME = new Rectangle(520, 380, 34, 75);
    private final Sprite kick;

    public FightController(Assets assets) {
        this.kick = new Fighter(assets.findFootPrint(), KICK_FRAME);
        kick.rotate(40);
    }

    public void draw(float delta, SpriteBatch batch) {
        kick.draw(batch);
    }
}
