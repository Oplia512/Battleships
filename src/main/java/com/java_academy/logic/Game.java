package com.java_academy.logic;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.NewGameState;
import com.java_academy.logic.state_machine.core.GameState;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.logic.tools.BSLog;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.java_academy.network.socket_provider.core.AbstractSocketProvider.CLOSE_MESSAGE;

/**
 * @author Siarhei Shauchenka
 * <p>
 * Provides game logic based on states machine
 */

public class Game implements OnMessageReceiverListener {

    private final Consumer<MessageObject> outputConsumer;
    private final AppCloseListener appCloseListener;
    private GameState currentState;


    /**
     * Creates entity of a Game class
     *
     * @param outputConsumer provides {@link MessageObject} to {@link com.java_academy.network.Connector} for sending
     */
    public Game(Consumer<MessageObject> outputConsumer, AppCloseListener appCloseListener) {
        this.outputConsumer = outputConsumer;
        this.appCloseListener = appCloseListener;
    }

    /**
     * start a game with NewGameState
     */
    public void startGame() {
        currentState = new NewGameState();
    }

    /**
     * Interface implementation which provides callbacks messages from Client
     *
     * @param messageSupplier provides message from {@link com.java_academy.network.Connector}
     */
    @Override
    public void onMessageReceived(Supplier<String> messageSupplier) {
        String message = messageSupplier.get();
        if (message.equals(CLOSE_MESSAGE)) {
            appCloseListener.onAppClosed();
        }

        if (!currentState.isEndingState()) {
            currentState.display(outputConsumer);
            BSLog.info(BSLog.getLogger(getClass()), message);
            currentState = currentState.changeState(message);
        }
    }
}
