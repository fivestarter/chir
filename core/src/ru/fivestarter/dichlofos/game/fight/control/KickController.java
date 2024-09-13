package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.fivestarter.dichlofos.game.common.Controller;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;

public class KickController implements Controller {
    private final FightSprite fightSprite;

    public static final int INIT_SCALE = 2;
    private float scale = INIT_SCALE;
    private float ttl = 0;

    public KickController(FightSprite fightSprite) {
        this.fightSprite = fightSprite;
    }

    @Override
    public Sprite getModel() {
        return fightSprite;
    }

    @Override
    public void draw(float delta, Batch batch) {
        if (isVisible()) {
            fightSprite.draw(batch);
            handle(delta);
        }
    }

    private void handle(float delta) {
        if (scale > 1) {
            scale -= 1.5f * delta;
        }
        fightSprite.setScale(scale);
        ttl -= delta;
    }

    public void start() {
        fightSprite.scale(INIT_SCALE);
        scale = INIT_SCALE;
        ttl = 1;
    }

    public boolean isVisible() {
        return ttl > 0;
    }
}