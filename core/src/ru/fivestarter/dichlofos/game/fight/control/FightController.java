package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;
import ru.fivestarter.dichlofos.utils.Assets;

public class FightController {
    public static final int WIDTH = 34;
    public static final int HEIGHT = 75;

    private static final Rectangle KICK_FRAME = new Rectangle(520, 380, WIDTH, HEIGHT);
    private final Sprite kick;
    private float kickScale = 2;

    public FightController(Assets assets) {
        this.kick = new FightSprite(assets.findFootPrint(), KICK_FRAME);
        kick.rotate(40);
        kick.scale(kickScale);
    }

    public void draw(float delta, SpriteBatch batch) {
        kick.draw(batch);
        handle(delta);
    }

    private void handle(float delta) {
        if (kickScale > 1) {
            kickScale -= 1.5f * delta;
        }
        kick.setScale(kickScale);
    }
}
