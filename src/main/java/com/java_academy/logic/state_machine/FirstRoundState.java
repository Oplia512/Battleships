package com.java_academy.logic.state_machine;


import com.java_academy.logic.checkers.WinnerChecker;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FirstRoundState implements GameState {

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "SHOOT FIRST"));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, "WAIT SECOND"));
    }

    @Override
    public GameState changeState(Supplier<String> message) {
        switch (message.get()) {
            case "shot":
                if (!WinnerChecker.ifShipsAreAlive()) {
                    return new EndGameState();
                }
                return this;
            case "miss":
                return new SecondRoundState();
            default:
                return new EndGameState();
        }
    }

    @Override
    public boolean isEndingState() {
        return false;
    }
}
