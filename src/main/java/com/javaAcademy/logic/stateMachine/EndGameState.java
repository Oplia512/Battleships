package com.javaAcademy.logic.stateMachine;


import java.util.function.Consumer;

import com.javaAcademy.logic.jsonModel.MessageCreator;
import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.GameState;

public class EndGameState implements GameState {

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, MessageCreator.createJsonMessageByKey("end.game")));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, MessageCreator.createJsonMessageByKey("end.game")));
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
