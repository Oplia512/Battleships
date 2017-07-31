package com.java_academy.logic.state_machine;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class PlayerEndActionState implements GameState {

    private Players players;

    PlayerEndActionState(Players players) {
        this.players = players;
    }

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {

    }

    @Override
    public GameState changeState(String message) {
        return null;
    }

    @Override
    public boolean isEndingState() {
        return false;
    }
}
