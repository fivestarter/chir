package ru.fivestarter.dichlofos.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.fivestarter.dichlofos.gta.model.World;
import ru.fivestarter.dichlofos.gta.model.character.human.Hero;
import ru.fivestarter.dichlofos.gta.view.WorldScreen;

public class HeroController {
    private final Hero hero;
    private final World world;

    private final float speed = 4f;

    public HeroController(Hero hero, World world) {
        this.hero = hero;
        this.world = world;
    }

    public void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveDown();
        }
    }

    private void moveDown() {
        float previousY = hero.getY();
        hero.setY(previousY - speed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveUp() {
        float previousY = hero.getY();
        hero.setY(previousY + speed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveRight() {
        float previousX = hero.getX();
        hero.setX(previousX + speed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void moveLeft() {
        float previousX = hero.getX();
        hero.setX(previousX - speed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void handleObstacleByX(float previousX) {
        if (world.isObstacle(hero.getPolygon())) {
            hero.setX(previousX);
        }
    }

    private void handleObstacleByY(float previousY) {
        if (world.isObstacle(hero.getPolygon())) {
            hero.setY(previousY);
        }
    }
}
