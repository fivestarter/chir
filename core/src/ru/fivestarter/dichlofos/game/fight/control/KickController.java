package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.fivestarter.dichlofos.game.common.Controller;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;

public class KickController implements Controller {
    private final FightSprite sprite;

    public static final int INIT_SCALE = 2;
    private float ttl = 0;

    public KickController(FightSprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Sprite getModel() {
        return sprite;
    }

    @Override
    public void draw(float delta, Batch batch) {
        if (isVisible()) {
            sprite.draw(batch);
            handle(delta);
        }
    }

    private void handle(float delta) {
        if (sprite.getScaleX() > 1) {
            sprite.scale(- 1.5f * delta);
        }
        ttl -= delta;
    }

    public void kick() {
        sprite.setScale(INIT_SCALE);
        ttl = 1;
    }

    public boolean isVisible() {
        return ttl > 0;
    }
}
