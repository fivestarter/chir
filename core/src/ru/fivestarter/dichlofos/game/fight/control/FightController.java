package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import ru.fivestarter.dichlofos.game.fight.model.kick.LeftHighKick;
import ru.fivestarter.dichlofos.game.fight.model.kick.RightHighKick;
import ru.fivestarter.dichlofos.utils.Assets;

import java.util.*;

public class FightController {

    private final KickController rightHighKickController;
    private final KickController rightMiddleKickController;
    private final KickController rightLowKickController;
    private final KickController leftHighKickController;
    private final KickController leftMiddleKickController;
    private final KickController leftLowKickController;

    private final List<Pair<KickController, Boolean>> combinationList;

    public FightController(Assets assets) {
        this.rightHighKickController = new KickController(new RightHighKick(assets, new Rectangle(535, 380, 34, 75)));
        this.rightMiddleKickController = new KickController(new RightHighKick(assets, new Rectangle(535, 330, 34, 75)));
        this.rightLowKickController = new KickController(new RightHighKick(assets, new Rectangle(535, 250, 34, 75)));
        this.leftHighKickController = new KickController(new LeftHighKick(assets, new Rectangle(545, 330, 34, 75)));
        this.leftMiddleKickController = new KickController(new LeftHighKick(assets, new Rectangle(545, 270, 34, 75)));
        this.leftLowKickController = new KickController(new LeftHighKick(assets, new Rectangle(545, 200, 34, 75)));

        combinationList = List.of(
                MutablePair.of(leftMiddleKickController, false),
                MutablePair.of(rightHighKickController, false));
    }

    public void draw(float delta, SpriteBatch batch) {
        rightHighKickController.draw(delta, batch);
        rightMiddleKickController.draw(delta, batch);
        rightLowKickController.draw(delta, batch);
        leftHighKickController.draw(delta, batch);
        leftMiddleKickController.draw(delta, batch);
        leftLowKickController.draw(delta, batch);
        handle();
    }

    private void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET) && !rightHighKickController.isVisible()) {
            rightHighKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P) && !leftHighKickController.isVisible()) {
            leftHighKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.APOSTROPHE) && !rightMiddleKickController.isVisible()) {
            rightMiddleKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON) && !leftMiddleKickController.isVisible()) {
            leftMiddleKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SLASH) && !rightLowKickController.isVisible()) {
            rightLowKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD) && !leftLowKickController.isVisible()) {
            leftLowKickController.kick();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.T) && !isCombinationVisible() && !isCombinationReady()) {
            combinationList.forEach(kickControllerBooleanPair -> kickControllerBooleanPair.setValue(true));
        }

        if (isCombinationReady()) {
            handleCombination();
        }
    }

    private void handleCombination() {
        ListIterator<Pair<KickController, Boolean>> combinationIterator = combinationList.listIterator();
        while (combinationIterator.hasNext()) {
            Pair<KickController, Boolean> combination = combinationIterator.next();
            if (combination.getValue()) {
                if (combinationIterator.hasPrevious()) {
                    combinationIterator.previous();
                }
                if (!combinationIterator.hasPrevious() || !combinationIterator.previous().getKey().isVisible()) {
                    combination.getKey().kick();
                    combination.setValue(false);
                }
                break;
            }
        }
    }

    private boolean isCombinationVisible() {
        return combinationList.stream().anyMatch(combination -> combination.getKey().isVisible());
    }

    private boolean isCombinationReady() {
        return combinationList.stream().anyMatch(Pair::getValue);
    }
}
