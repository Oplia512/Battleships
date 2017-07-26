package com.java_academy.logic.state_machine.core;


import com.java_academy.logic.model.MessageObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {
    void display(Consumer<MessageObject> displayConsumer);
    GameState changeState(Supplier<String> message);
    boolean isEndingState();
}
