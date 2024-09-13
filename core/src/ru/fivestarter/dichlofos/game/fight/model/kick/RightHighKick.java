package ru.fivestarter.dichlofos.game.fight.model.kick;

import com.badlogic.gdx.math.Rectangle;
import ru.fivestarter.dichlofos.game.fight.model.FightSprite;
import ru.fivestarter.dichlofos.utils.Assets;

public class RightHighKick extends FightSprite{

    public static final int INIT_SCALE = 2;

    public RightHighKick(Assets assets, Rectangle rectangle) {
        super(assets.findFootPrint(), rectangle);
        rotate(40);
        scale(INIT_SCALE);
    }
}
