package com.java_academy.logic.state_machine;

import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

/**
 * @author Siarhei Shauchenka
 */

@Test
public class GameTest {

    @DataProvider(name = "message Provider")
    private Object[][] getMessages(){
        Consumer<MessageObject> consumer = message -> System.out.println(message.getMessage());
        Game game = new Game(consumer);
        return new Object[][]{
                {1, game, "shot"},
                {2, game, "miss"},
                {3, game, "shot"},
                {4, game, "shot"},
                {5, game, "shot"},
                {6, game, "shot"},
        };
    }


    @Test(dataProvider = "message Provider")
    public void gameTest(int testCase, Game game, String message){
        game.onMessageReceived(() -> message);
        switch (testCase){
            case 1:
                assertEquals(game.isFinished(), false);
                break;
            case 2:
                assertEquals(game.isFinished(), false);
                break;
            case 3:
                assertEquals(game.isFinished(), false);
                break;
            case 4:
                assertEquals(game.isFinished(), false);
                break;
            case 5:
                assertEquals(game.isFinished(), false);
                break;
            case 6:
                assertEquals(game.isFinished(), true);
                break;

        }
    }
}
