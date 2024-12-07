package com.design.patterns.facade.hometheatre;

import com.design.patterns.facade.hometheatre.items.AudioSystem;
import com.design.patterns.facade.hometheatre.items.DVDPlayer;
import com.design.patterns.facade.hometheatre.items.PlayStation;

import java.util.List;

public class HomeTheatreFacade {
    private final List<HomeTheatre> homeTheatreItems;

    public HomeTheatreFacade() {
        homeTheatreItems = List.of(
                new AudioSystem(),
                new DVDPlayer(),
                new PlayStation()
        );
    }

    public void startHomeTheatre() {
        makeActions(homeTheatreItems,
                HomeTheatre.ItemOperation.POWER_ON,
                HomeTheatre.ItemOperation.PLAY_MOVIE,
                HomeTheatre.ItemOperation.PAUSE_MOVIE,
                HomeTheatre.ItemOperation.PLAY_SOUND,
                HomeTheatre.ItemOperation.PAUSE_SOUND,
                HomeTheatre.ItemOperation.PLAY_GAME,
                HomeTheatre.ItemOperation.PAUSE_GAME,
                HomeTheatre.ItemOperation.POWER_OFF
                );
    }

    private void makeActions(List<HomeTheatre> homeTheatres, HomeTheatre.ItemOperation... itemOperations) {
        homeTheatres.forEach(homeTheatreItem -> homeTheatreItem.itemOperation(itemOperations));
    }
}
