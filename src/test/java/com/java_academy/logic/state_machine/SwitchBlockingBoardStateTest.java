package com.java_academy.logic.state_machine;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.logic.model.Players;

public class SwitchBlockingBoardStateTest {
	
    private SwitchBlockingBoardState switchBlockingBoardState;
    private Players player;

    @BeforeTest
    public void setUp(){
        player = Players.FIRST_PLAYER;
        switchBlockingBoardState = new SwitchBlockingBoardState(player);
    }
	
    @Test
    public void checkIfItIsEndingState(){
        assertFalse(switchBlockingBoardState.isEndingState());
    }
    
    @Test
    public void  checkIfNextStateIsSetFleetState(){
        assertTrue(switchBlockingBoardState.changeState(null) instanceof PlayerActionState);
    }
}
