package com.java_academy.logic.state_machine;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.network.Connector;

public class WhoStartStateTest {
	
	private WhoStartState whoStartState;
	private Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        whoStartState = new WhoStartState();
    }
    
    @Test
    public void checkIfItIsEndingState(){
        assertFalse(whoStartState.isEndingState());
    }
    
    @Test
    public void  checkIfNextStateIsSetFleetState(){
        assertTrue(whoStartState.changeState(null) instanceof PlayerActionState);
    }
    
    @Test
	public void testDisplay() {
    	whoStartState.display(connector::sendMessage);
	}
}
