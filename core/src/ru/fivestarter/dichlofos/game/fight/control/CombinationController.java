package ru.fivestarter.dichlofos.game.fight.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.ListIterator;

public class CombinationController {
    private final List<MutablePair<KickController, Boolean>> combinationList;

    public CombinationController(List<KickController> controllers) {
        combinationList = controllers.stream()
                .map(kickController -> MutablePair.of(kickController, false))
                .toList();
    }

    public void draw(float delta, SpriteBatch batch) {
        combinationList.forEach(combination -> combination.getKey().draw(delta, batch));

        if (isLoaded()) {
            handle();
        }
    }

    private void handle() {
        ListIterator<MutablePair<KickController, Boolean>> combinationIterator = combinationList.listIterator();
        while (combinationIterator.hasNext()) {
            Pair<KickController, Boolean> combination = combinationIterator.next();
            if (combination.getValue()) {
                if (combinationIterator.hasPrevious()) {
                    combinationIterator.previous();
                }
                if (!combinationIterator.hasPrevious() || !combinationIterator.previous().getKey().isOn()) {
                    combination.getKey().kick();
                    combination.setValue(false);
                }
                break;
            }
        }
    }

    public boolean isOn() {
        return combinationList.stream().anyMatch(combination -> combination.getKey().isOn());
    }

    public boolean isLoaded() {
        return combinationList.stream().anyMatch(Pair::getValue);
    }

    public void load() {
        combinationList.forEach(kickControllerBooleanPair -> kickControllerBooleanPair.setValue(true));
    }

    public boolean isActive() {
        return isOn() || isLoaded();
    }
}
