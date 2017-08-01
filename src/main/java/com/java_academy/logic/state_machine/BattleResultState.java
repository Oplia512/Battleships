package com.java_academy.logic.state_machine;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class BattleResultState implements GameState {
    private Players winner;

    public BattleResultState(Players winner) {
        this.winner = winner;
    }

    @Override
    public void display(Consumer<MessageObject> outputConsumer) {
        outputConsumer.accept(new MessageObject(winner, "YOU WIN!"));
        outputConsumer.accept(new MessageObject(winner.getOpponent(), "YOU LOST!"));
    }

    @Override
    public GameState changeState(String inputMessage) {
        return new EndGameState();
    }

    @Override
    public boolean isEndingState() {
        return false;
    }
}
