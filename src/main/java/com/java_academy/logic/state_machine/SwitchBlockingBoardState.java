package com.java_academy.logic.state_machine;

import com.java_academy.logic.json_model.MessageCreator;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Artur
 */
public class SwitchBlockingBoardState implements GameState {

    private final Players currentPlayer;

    public SwitchBlockingBoardState(Players currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(currentPlayer, MessageCreator.createJsonMessageByKey("your.turn")));
        displayConsumer.accept(new MessageObject(currentPlayer.getOpponent(), MessageCreator.createJsonMessageByKey("not.your.turn")));
    }

    @Override
    public GameState changeState(String message) {
        return new PlayerActionState(currentPlayer);
    }
}
