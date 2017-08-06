package com.java_academy.logic.state_machine;

import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.PlayerEndActionState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class PlayerEndActionStateTest {

    private PlayerEndActionState playerEndActionState;
    private Players player1;
    private Players player2;

    @BeforeTest
    public void setUp(){
        player1 = Players.FIRST_PLAYER;
        playerEndActionState = new PlayerEndActionState(player1, null);
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(playerEndActionState.isEndingState());
    }

}
