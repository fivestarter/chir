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

    private final float moveSpeed = 2f;
    private final float runSpeed = 4f;

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
            if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
                runLeft();
            } else {
                moveLeft();
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
                runRight();
            } else {
                moveRight();
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
                runUp();
            } else {
                moveUp();
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
                runDown();
            } else {
                moveDown();
            }
        } else {
            stop();
        }
    }

    private void moveDown() {
        float previousY = hero.getY();
        hero.moveDown(previousY - moveSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void runDown() {
        float previousY = hero.getY();
        hero.runDown(previousY - runSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveUp() {
        float previousY = hero.getY();
        hero.moveUp(previousY + moveSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void runUp() {
        float previousY = hero.getY();
        hero.runUp(previousY + runSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByY(previousY);
    }

    private void moveRight() {
        float previousX = hero.getX();
        hero.moveRight(previousX + moveSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void runRight() {
        float previousX = hero.getX();
        hero.runRight(previousX + runSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void moveLeft() {
        float previousX = hero.getX();
        hero.moveLeft(previousX - moveSpeed * WorldScreen.DELTA_CFF);
        handleObstacleByX(previousX);
    }

    private void runLeft() {
        float previousX = hero.getX();
        hero.runLeft(previousX - runSpeed * WorldScreen.DELTA_CFF);
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
