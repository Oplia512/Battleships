package com.java_academy.logic.model;

/**
 * @author Siarhei Shauchenka
 * @since 01.08.2017
 *
 * Game's players representation
 */

public enum Players {
    FIRST_PLAYER(new Player("First", 3)) {
        @Override
        public Players getOpponent() {
            return Players.SECOND_PLAYER;
        }
    },
    SECOND_PLAYER(new Player("Second", 3)) {
        @Override
        public Players getOpponent() {
            return FIRST_PLAYER;
        }
    };

    private final Player player;

    Players(Player player) {
        this.player = player;
    }

    public abstract Players getOpponent();

    public Player getPlayer() {
        return player;
    }
}
