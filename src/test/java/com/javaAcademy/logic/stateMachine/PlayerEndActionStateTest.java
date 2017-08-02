package com.javaAcademy.logic.stateMachine;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.PlayerEndActionState;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class PlayerEndActionStateTest {

    private PlayerEndActionState playerEndActionState;
    private Players player;

    @BeforeTest
    public void setUp(){
        player = Players.FIRST_PLAYER;
        playerEndActionState = new PlayerEndActionState(player, null);
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(playerEndActionState.isEndingState());
    }

}
