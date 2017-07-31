package com.java_academy.logic;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.NewGameState;
import com.java_academy.logic.state_machine.core.GameState;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Siarhei Shauchenka
 *
 * Provides game logic based on states machine
 */

public class Game implements OnMessageReceiverListener{

    private Consumer<MessageObject> outputConsumer;
    private GameState currentState;
    private boolean isFinished;

    /**
     * Creates entity of a Game class and start a game with NewGameState
     * @param outputConsumer
     */
    public Game(Consumer<MessageObject> outputConsumer) {
        this.outputConsumer = outputConsumer;
        currentState = new NewGameState();
    }


    /**
     * Interface implementation which provides callbacks messages from Client
     * @param messageSupplier
     */
    @Override
    public void onMessageReceived(Supplier<String> messageSupplier) {
        if (!isFinished){
            currentState.display(outputConsumer);
            currentState = currentState.changeState(messageSupplier);
            isFinished = currentState.isEndingState();
        }
    }

    public boolean isFinished() {
        return isFinished;
    }
}
