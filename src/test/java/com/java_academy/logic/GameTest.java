package com.java_academy.logic;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.network.Connector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Siarhei Shauchenka
 * @since 04.08.17
 */
public class GameTest {


    @Test
    public void createGameInstanceTest(){
        Game game = new Game(null, null);
        Assert.assertNotNull(game);
    }

    @Test
    public void startGameTest(){
        GameClassTest game = new GameClassTest(null);
        game.startGame();
        Assert.assertNotNull(game.startGameMarker);
        Assert.assertTrue(game.startGameMarker);
    }


    @Test
    public void messageReceiveTest(){
        GameClassTest game = new GameClassTest(messageObject -> System.out.println(messageObject.getMessage()));
        game.startGame();
        game.onMessageReceived(() -> "Test");
        Assert.assertNotNull(game.onMessageReceiveMarker);
        Assert.assertTrue(game.onMessageReceiveMarker);
    }

    private class GameClassTest extends Game {

        Boolean startGameMarker;
        Boolean onMessageReceiveMarker;

        /**
         * Creates entity of a Game class
         *
         * @param outputConsumer provides {@link MessageObject} to {@link Connector} for sending
         */
        public GameClassTest(Consumer<MessageObject> outputConsumer) {
            super(outputConsumer, null);

        }

        @Override
        public void startGame() {
            super.startGame();
            startGameMarker = true;
        }

        @Override
        public void onMessageReceived(Supplier<String> messageSupplier) {
            super.onMessageReceived(messageSupplier);
            onMessageReceiveMarker = true;
        }
    }
}
