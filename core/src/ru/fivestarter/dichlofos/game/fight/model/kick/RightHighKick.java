package ru.fivestarter.dichlofos.game.fight.model.kick;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.common.Controller;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;
import ru.fivestarter.dichlofos.utils.Assets;

public class RightHighKick extends FightSprite implements Controller {

    public static final int INIT_SCALE = 2;
    private float scale = INIT_SCALE;
    private float ttl = 0;

    public RightHighKick(Assets assets, Rectangle rectangle) {
        super(assets.findFootPrint(), rectangle);
        rotate(40);
        scale(scale);
    }

    @Override
    public void draw(float delta, Batch batch) {
        if (isVisible()) {
            draw(batch);
            handle(delta);
        }
    }

    private void handle(float delta) {
        if (scale > 1) {
            scale -= 1.5f * delta;
        }
        setScale(scale);
        ttl -= delta;
    }

    @Override
    public Sprite getModel() {
        return this;
    }

    public void start() {
        scale = INIT_SCALE;
        ttl = 1;
    }

    public boolean isVisible() {
        return ttl > 0;
    }
}
