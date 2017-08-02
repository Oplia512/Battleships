package com.javaAcademy.logic.stateMachine;

import java.util.function.Consumer;

import com.javaAcademy.logic.attacks.Attack;
import com.javaAcademy.logic.attacks.NormalAttack;
import com.javaAcademy.logic.attacks.NukeAttack;
import com.javaAcademy.logic.jsonModel.MarkedIndexes;
import com.javaAcademy.logic.jsonModel.MessageCreator;
import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.GameState;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class PlayerActionState implements GameState {

    private Players currentPlayer;

    public PlayerActionState(Players currentPlayer){
        this.currentPlayer = currentPlayer;
    }
    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
    	displayConsumer.accept(new MessageObject(currentPlayer, MessageCreator.createJsonMessageByKey("your.turn")));
        displayConsumer.accept(new MessageObject(currentPlayer.getOpponent(), MessageCreator.createJsonMessageByKey("not.your.turn")));
   }

    @Override
    public GameState changeState(String inputMessage) {
    	MarkedIndexes markedIndexes;

        if (currentPlayer.getPlayer().canUseNuke() && inputMessage.startsWith("n")) {
            markedIndexes = dropNuke(inputMessage.replace("n", ""));
        } else {
            markedIndexes = shootOnField(inputMessage);
        }

        return new PlayerEndActionState(currentPlayer, markedIndexes);
    }
    
    private MarkedIndexes dropNuke(String inputMessage) {
    	Integer index = Integer.parseInt(inputMessage);
    	Attack attack = new NukeAttack(currentPlayer.getOpponent().getPlayer().getBoard());
    	return attack.attack(index);
    }
    
    private MarkedIndexes shootOnField(String inputMessage) {
    	Integer index = Integer.parseInt(inputMessage);
    	Attack attack = new NormalAttack(currentPlayer.getOpponent().getPlayer().getBoard() );
    	return attack.attack(index);

    }
}
