package ru.fivestarter.dichlofos.game.fight.model.kick;

import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;
import ru.fivestarter.dichlofos.utils.Assets;

public class LeftHighKick extends FightSprite {

    public LeftHighKick(Assets assets, Rectangle rectangle) {
        super(assets.findFootPrint(), rectangle);
        rotate(-40);
        setFlip(true, false);
    }
}
