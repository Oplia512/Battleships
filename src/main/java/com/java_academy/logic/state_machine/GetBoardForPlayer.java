package com.java_academy.logic.state_machine;

import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.json_model.MessageCreator;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 8/2/2017
 */
public class GetBoardForPlayer implements GameState {

    private final MarkedIndexes firstPlayerBoard;
    private final MarkedIndexes secondPlayerBoard;

    GetBoardForPlayer(MarkedIndexes firstPlayerBoard, MarkedIndexes secondPlayerBoard) {
		this.firstPlayerBoard = firstPlayerBoard;
		this.secondPlayerBoard = secondPlayerBoard;
	}

	@Override
    public synchronized void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, MessageCreator.createJsonMarkedIndexes(firstPlayerBoard)));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, MessageCreator.createJsonMarkedIndexes(secondPlayerBoard)));
    }

    @Override
    public synchronized GameState changeState(String message) {
        return new WhoStartState();
    }
}
