package com.javaAcademy.logic.stateMachine.core;


import java.util.function.Consumer;

import com.javaAcademy.logic.model.MessageObject;
/**
 * One step in Game State Machine
 * 3 methods:
 * display - send data from server to client
 * changeState - do state logic and return new state
 * isEndingState - check state, default is not end state
 * */
public interface GameState {
	
    void display(Consumer<MessageObject> displayConsumer);
    GameState changeState(String message);
    
    default boolean isEndingState() {
    	return false;
    }
}
