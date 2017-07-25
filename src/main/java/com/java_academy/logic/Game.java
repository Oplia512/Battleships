package com.java_academy.logic;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.NewGameState;
import com.java_academy.logic.state_machine.core.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private Supplier<String> inputSuplier;
    private Consumer<MessageObject> outputConsumer;

    public Game(Supplier<String> inputSuplier, Consumer<MessageObject> outputConsumer) {
        this.inputSuplier = inputSuplier;
        this.outputConsumer = outputConsumer;
    }

    public void startGame() {
        GameState currentState= new NewGameState();
        while(!currentState.isEndingState()){
            currentState.display(outputConsumer);
            currentState = currentState.changeState(inputSuplier);
        }

    }

}
