package com.design.patterns.facade.hometheatre.items;

import com.design.patterns.facade.hometheatre.HomeTheatre;

import java.util.List;

public class DVDPlayer extends HomeTheatre {

    private static final List<ItemOperation> supportedOperations;

    static {
        supportedOperations = List.of(
                ItemOperation.POWER_OFF,
                ItemOperation.POWER_ON,
                ItemOperation.PLAY_MOVIE,
                ItemOperation.PAUSE_MOVIE
        );
    }

    @Override
    public String itemName() {
        return "DVD Player";
    }

    @Override
    public String operate(ItemOperation itemOperation) {
        if (supportedOperations.contains(itemOperation)) {
            return itemName();
        }
        return null;
    }
}
