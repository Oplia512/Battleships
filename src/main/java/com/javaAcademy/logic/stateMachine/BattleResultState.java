package com.javaAcademy.logic.stateMachine;

import java.util.function.Consumer;

import com.javaAcademy.logic.jsonModel.MessageCreator;
import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.GameState;

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
    	outputConsumer.accept(new MessageObject(winner, MessageCreator.createJsonMessageByKey("you.win")));
    	outputConsumer.accept(new MessageObject(winner.getOpponent(), MessageCreator.createJsonMessageByKey("you.lose")));
    }

    @Override
    public GameState changeState(String inputMessage) {
        return new EndGameState();
    }

}
