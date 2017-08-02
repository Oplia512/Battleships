package com.java_academy.logic.state_machine;

import com.java_academy.logic.jsonModel.MarkedIndexes;
import com.java_academy.logic.jsonModel.MessageCreator;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 8/2/2017
 */
public class GetBoardForPlayer implements GameState {

    private MarkedIndexes firstPlayerBoard;
    private MarkedIndexes secondPlayerBoard;

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, MessageCreator.createJsonMarkedIndexes(firstPlayerBoard)));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, MessageCreator.createJsonMarkedIndexes(secondPlayerBoard)));
    }

    @Override
    public GameState changeState(String message) {
        //TODO handling user input about fleet settings

        return new PlayerActionState(Players.FIRST_PLAYER);
    }
}
