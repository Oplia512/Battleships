package com.java_academy.logic.state_machine;

import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.json_model.MessageCreator;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class PlayerEndActionState implements GameState {

    private final Players currentPlayer;
    private Boolean someoneWon;
    private final MarkedIndexes markedIndexes;

    PlayerEndActionState(Players currentPlayer, MarkedIndexes markedIndexes) {
        this.currentPlayer = currentPlayer;
        this.markedIndexes = markedIndexes;
    }

    @Override
    public void display(Consumer<MessageObject> displayConsumer) {
        someoneWon = checkIfPlayerWon(currentPlayer);
    	markedIndexes.setIsMyBoard(false);
    	markedIndexes.setIsNukeAvailable(currentPlayer.getPlayer().canUseNuke());
    	markedIndexes.setHitAndSink(currentPlayer.getOpponent().getPlayer().hitAndSink());
    	markedIndexes.setEndOfGame(someoneWon);
    	displayConsumer.accept(new MessageObject(currentPlayer, MessageCreator.createJsonMarkedIndexes(markedIndexes)));
		
    	markedIndexes.setIsMyBoard(true);
		markedIndexes.setIsNukeAvailable(currentPlayer.getOpponent().getPlayer().canUseNuke());
		markedIndexes.setHitAndSink(false);
		markedIndexes.setEndOfGame(someoneWon);
		displayConsumer.accept(new MessageObject(currentPlayer.getOpponent(), MessageCreator.createJsonMarkedIndexes(markedIndexes)));
    }

    @Override
    public GameState changeState(String message) {
        Boolean hasBeenHit = somethingWasHit(markedIndexes.getMap());
        if(someoneWon) {
            return new BattleResultState(currentPlayer);
        } if(hasBeenHit) {
            return new PlayerActionState(currentPlayer);
        } else {
            return new SwitchBlockingBoardState(currentPlayer.getOpponent());
        }
    }

    boolean checkIfPlayerWon(Players player) {
        return player.getOpponent().getPlayer().hasNoFleet();
    }

    public Boolean somethingWasHit(Map<Integer, Boolean> map) {
        for(Entry<Integer, Boolean> markIndex: map.entrySet()) {
			if(markIndex.getValue().equals(true)) {
				return true;
			}
		}
        return false;
    }
}
