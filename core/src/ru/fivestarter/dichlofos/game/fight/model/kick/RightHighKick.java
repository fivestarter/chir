package ru.fivestarter.dichlofos.game.fight.model.kick;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.common.Controller;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;
import ru.fivestarter.dichlofos.utils.Assets;

public class RightHighKick extends FightSprite implements Controller {

    private float kickScale = 2;

    public RightHighKick(Assets assets, Rectangle rectangle) {
        super(assets.findFootPrint(), rectangle);
        rotate(40);
        scale(kickScale);
    }

    @Override
    public void draw(float delta, Batch batch) {
        draw(batch);
        handle(delta);
    }

    private void handle(float delta) {
        if (kickScale > 1) {
            kickScale -= 1.5f * delta;
        }
        setScale(kickScale);
    }

    @Override
    public Sprite getModel() {
        return null;
    }
}
