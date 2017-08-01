package com.java_academy.logic.state_machine.core;


import com.java_academy.logic.model.MessageObject;

import java.util.function.Consumer;
import java.util.function.Supplier;
/**
 * One step in Game State Machine
 * 3 methods:
 * display - send data from server to client
 * changeState - do state logic and return new state
 * isEndingState - check state
 * */
public interface GameState {
    void display(Consumer<MessageObject> displayConsumer);
    GameState changeState(String message);
    boolean isEndingState();
}
