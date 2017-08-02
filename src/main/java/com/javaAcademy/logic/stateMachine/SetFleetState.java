package com.javaAcademy.logic.stateMachine;

import java.util.function.Consumer;

import com.javaAcademy.logic.jsonModel.MessageCreator;
import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Player;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.GameState;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class SetFleetState implements GameState {

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
    	displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, MessageCreator.createJsonMessageByKey("set.fleet")));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, MessageCreator.createJsonMessageByKey("set.fleet")));
    }

    @Override
    public GameState changeState(String message) {
        //TODO handling user input about fleet settings
        createBoardWithFleet(Players.FIRST_PLAYER.getPlayer());
        createBoardWithFleet(Players.SECOND_PLAYER.getPlayer());

        return new PlayerActionState(Players.FIRST_PLAYER);
    }

    private void createBoardWithFleet(Player player) {
        //TODO SHIPYARD USER
        player.createFleet();
    }
}
