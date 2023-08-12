package ru.fivestarter.dichlofos.game.gta.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.fivestarter.dichlofos.game.common.CharacterController;
import ru.fivestarter.dichlofos.game.gta.model.World;
import ru.fivestarter.dichlofos.game.gta.model.character.human.Hero;
import ru.fivestarter.dichlofos.game.gta.view.WorldScreen;

import static ru.fivestarter.dichlofos.utils.Assets.GTA_HERO_ATLAS_FILE_NAME;

public class HeroController implements CharacterController<Sprite> {
    private static final int X = 40;
    private static final int Y = 49;
    private final Hero hero;
    private final World world;

    private final float speed = 2f;

    public HeroController(AssetManager assetManager, World world) {
        this.hero = new Hero(assetManager.get(GTA_HERO_ATLAS_FILE_NAME, TextureAtlas.class), X, Y);
        this.world = world;
    }

    @Override
    public Hero getModel() {
        return hero;
    }

    @Override
    public void draw(Batch batch) {
        hero.draw(batch);
        handle();
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveDown();
        } else {
            stop();
        }
    }

    private void moveDown() {
        float previousY = hero.getY();
        hero.moveDown(previousY - speed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveUp() {
        float previousY = hero.getY();
        hero.moveUp(previousY + speed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveRight() {
        float previousX = hero.getX();
        hero.moveRight(previousX + speed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void moveLeft() {
        float previousX = hero.getX();
        hero.moveLeft(previousX - speed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void handleObstacleByX(float previousX) {
        if (world.isObstacle(hero.getFootRectangle())) {
            hero.setX(previousX);
        }
    }

    private void handleObstacleByY(float previousY) {
        if (world.isObstacle(hero.getFootRectangle())) {
            hero.setY(previousY);
        }
    }

    private void stop() {
        hero.stop();
    }

}
