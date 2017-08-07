package com.java_academy.logic.state_machine;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.logic.model.Players;
import com.java_academy.network.Connector;

public class SwitchBlockingBoardStateTest {
	
    private SwitchBlockingBoardState switchBlockingBoardState;
    private Players player;
    private Connector connector = mock(Connector.class);

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
    
    @Test
	public void testDisplay() {
    	switchBlockingBoardState.display(connector::sendMessage);
	}
}
