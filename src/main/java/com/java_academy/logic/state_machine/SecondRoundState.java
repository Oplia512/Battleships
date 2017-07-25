package com.java_academy.logic.state_machine;

import com.java_academy.logic.Players;
import com.java_academy.logic.WinnerChecker;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SecondRoundState implements GameState {
    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, "SHOOT"));
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "WAIT"));
    }

    @Override
    public GameState changeState(Supplier<String> message) {
        switch (message.get()) {
            case "shot":
                if (!WinnerChecker.ifShipsAreAlive()) {
                    return new EndGameState();
                }
                return this;
            default:
                return new FirstRoundState();
        }
    }


    @Override
    public boolean isEndingState() {
        return false;
    }
}
