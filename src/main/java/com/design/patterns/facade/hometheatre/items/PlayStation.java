package com.design.patterns.facade.hometheatre.items;

import com.design.patterns.facade.hometheatre.HomeTheatre;

import java.util.List;

public class PlayStation extends HomeTheatre {
    private static final List<ItemOperation> supportedOperations;

    static {
        supportedOperations = List.of(
                ItemOperation.POWER_OFF,
                ItemOperation.POWER_ON,
                ItemOperation.PLAY_GAME,
                ItemOperation.PAUSE_GAME
        );
    }

    @Override
    public String itemName() {
        return "Play Station";
    }

    @Override
    public String operate(ItemOperation itemOperation) {
        if (supportedOperations.contains(itemOperation)) {
            return itemName();
        }
        return null;
    }

}


