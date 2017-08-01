package com.java_academy.logic.state_machine;

import com.java_academy.logic.attacks.Attack;
import com.java_academy.logic.attacks.NormalAttack;
import com.java_academy.logic.attacks.NukeAttack;
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

    public PlayerActionState(Players currentPlayer){
        this.currentPlayer = currentPlayer;
    }
    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        displayConsumer.accept(new MessageObject(Players.FIRST_PLAYER, "SHOT"));
        displayConsumer.accept(new MessageObject(Players.SECOND_PLAYER, "WAIT"));
    }

    @Override
    public GameState changeState(String inputMessage) {

        if (currentPlayer.getPlayer().canUseNuke() && inputMessage.startsWith("n")) {
            //markedIndexes = dropNuke(inputMessage.replace("n", ""));
            Integer index = Integer.parseInt(inputMessage);
            Attack attack = new NukeAttack(currentPlayer.getOpponent().getPlayer().getBoard());
            attack.attack(index);
        } else {
            //markedIndexes = shootOnField(inputMessage);
            Integer index = Integer.parseInt(inputMessage);
            Attack attack = new NormalAttack(currentPlayer.getOpponent().getPlayer().getBoard());
            attack.attack(index);
        }

        return new PlayerEndActionState(currentPlayer);
    }

    @Override
    public boolean isEndingState() {
        return false;
    }
}
