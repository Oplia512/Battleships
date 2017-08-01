package com.java_academy.logic.state_machine;


import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndGameState implements GameState {

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "End of game"));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, "End of game"));
    }

    @Override
    public GameState changeState(String message) {
        return null;
    }

    @Override
    public boolean isEndingState() {
        return true;
    }


}
