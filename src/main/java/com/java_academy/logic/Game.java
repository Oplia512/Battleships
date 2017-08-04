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

    /**
     * Creates entity of a Game class
     * @param outputConsumer provides {@link MessageObject} to {@link com.java_academy.network.Connector} for sending
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
     * @param messageSupplier provides message from {@link com.java_academy.network.Connector}
     */
    @Override
    public void onMessageReceived(Supplier<String> messageSupplier) {
        if (!currentState.isEndingState()){
            //System.out.println("          Przed wyswietleniem");
            currentState.display(outputConsumer);
            //System.out.println("          Po wyswietleniu, przed zmiana stanu");
            currentState = currentState.changeState(messageSupplier.get());
            //System.out.println("          Po wyswietleniu, po zmianie stanu");
        } else {
            if(messageSupplier.get().equals("")) {

            }
        }
    }
}
