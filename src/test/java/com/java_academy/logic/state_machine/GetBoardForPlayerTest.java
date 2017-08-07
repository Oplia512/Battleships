package com.java_academy.logic.state_machine;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.network.Connector;

public class GetBoardForPlayerTest {

	private GetBoardForPlayer getBoardForPlayer;
	private Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
    	getBoardForPlayer = new GetBoardForPlayer(null, null);
    }
    
    @Test
    public void checkIfItIsEndingState(){
        assertFalse(getBoardForPlayer.isEndingState());
    }
    
    @Test
    public void  checkIfNextStateIsSetFleetState(){
        assertTrue(getBoardForPlayer.changeState(null) instanceof WhoStartState);
    }
    
    @Test
	public void testDisplay() {
    	getBoardForPlayer.display(connector::sendMessage);
	}
}
