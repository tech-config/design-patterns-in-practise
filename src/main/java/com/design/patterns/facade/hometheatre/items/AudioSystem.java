package com.design.patterns.facade.hometheatre.items;

import com.design.patterns.facade.hometheatre.HomeTheatre;

import java.util.List;

public class AudioSystem extends HomeTheatre {
    private static final List<ItemOperation> supportedOperations;

    static {
        supportedOperations = List.of(
                ItemOperation.POWER_OFF,
                ItemOperation.POWER_ON,
                ItemOperation.PLAY_SOUND,
                ItemOperation.PAUSE_SOUND
        );
    }

    @Override
    public String itemName() {
        return "Audio System";
    }

    @Override
    public String operate(ItemOperation itemOperation) {
        if (supportedOperations.contains(itemOperation)) {
            return itemName();
        }
        return null;
    }
}
