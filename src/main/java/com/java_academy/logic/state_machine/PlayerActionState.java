package com.java_academy.logic.state_machine;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class PlayerActionState implements GameState {

    private Players currentPlayer;

    PlayerActionState(Players currentPlayer){
        this.currentPlayer = currentPlayer;
    }
    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "SHOT"));
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "WAIT"));
    }

    @Override
    public GameState changeState(String message) {
        if (currentPlayer.getPlayer().canUseNuke() && message.startsWith("n")) {
            //markedIndexes = dropNuke(inputMessage.replace("n", ""));
        } else {
            //markedIndexes = shootOnField(inputMessage);
        }

        return new PlayerEndActionState(currentPlayer);
    }

    @Override
    public boolean isEndingState() {
        return false;
    }
}
