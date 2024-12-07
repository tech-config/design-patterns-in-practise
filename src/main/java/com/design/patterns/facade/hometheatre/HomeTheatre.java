package com.design.patterns.facade.hometheatre;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static java.util.Optional.ofNullable;

@Slf4j
public abstract class HomeTheatre {

    public void powerOff(ItemOperation itemOperation) {
        ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is powering off", itemName);
                });
    }

    public void powerOn(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is powering on", itemName);
                });
    }

    public void playMovie(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is playing movie", itemName);
                });
    }

    public void pauseMovie(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is pausing movie", itemName);
                });
    }

    public void playGame(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is playing game", itemName);
                });
    }

    public void pauseGame(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is pausing game", itemName);
                });
    }

    public void playSound(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is playing sound", itemName);
                });
    }

    public void pauseSound(ItemOperation itemOperation) {
                ofNullable(operate(itemOperation))
                .ifPresent(itemName -> {
                    log.info("{} is pausing sound", itemName);
                });
    }

    private void operateItem(ItemOperation itemOperation) {
        // using switch pattern matching feature of java 21
        switch (itemOperation) {
            case POWER_OFF -> powerOff(itemOperation);
            case POWER_ON -> powerOn(itemOperation);
            case PLAY_GAME -> playGame(itemOperation);
            case PAUSE_GAME -> pauseGame(itemOperation);
            case PLAY_SOUND -> playSound(itemOperation);
            case PAUSE_SOUND -> pauseSound(itemOperation);
            case PLAY_MOVIE -> playMovie(itemOperation);
            case PAUSE_MOVIE -> pauseMovie(itemOperation);
        }
    }

    /**
     * perform operations
     *
     * @param itemOperations
     */
    public void itemOperation(ItemOperation... itemOperations) {
        Arrays.stream(itemOperations).forEach(this::operateItem);
    }

    public abstract String itemName();

    public abstract String operate(ItemOperation itemOperation);

    public enum ItemOperation {
        POWER_ON, PLAY_MOVIE, PAUSE_MOVIE, PLAY_SOUND, PAUSE_SOUND, PLAY_GAME, PAUSE_GAME, POWER_OFF
    }

}
