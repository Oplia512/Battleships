package com.javaAcademy.logic;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.stateMachine.NewGameState;
import com.javaAcademy.logic.stateMachine.core.GameState;
import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;

/**
 * @author Siarhei Shauchenka
 *
 * Provides game logic based on states machine
 */

public class Game implements OnMessageReceiverListener{

    private Consumer<MessageObject> outputConsumer;
    private GameState currentState;

    /**
     * Creates entity of a Game class
     * @param outputConsumer provides {@link MessageObject} to {@link com.javaAcademy.network.Connector} for sending
     */
    public Game(Consumer<MessageObject> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    /**
     * start a game with NewGameState
     */
    public void startGame(){
        currentState= new NewGameState();
    }


    /**
     * Interface implementation which provides callbacks messages from Client
     * @param messageSupplier provides message from {@link com.javaAcademy.network.Connector}
     */
    @Override
    public void onMessageReceived(Supplier<String> messageSupplier) {
        if (!currentState.isEndingState()){
            currentState.display(outputConsumer);
            currentState = currentState.changeState(messageSupplier.get());
        }
    }
}
