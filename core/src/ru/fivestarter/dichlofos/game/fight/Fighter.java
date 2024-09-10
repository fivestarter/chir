package ru.fivestarter.dichlofos.game.fight;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Fighter extends Sprite {
    public Fighter(TextureRegion region, Rectangle rectangle) {
        super(region);
        setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
}
