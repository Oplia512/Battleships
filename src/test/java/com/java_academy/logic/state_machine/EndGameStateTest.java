package com.java_academy.logic.state_machine;

import com.java_academy.logic.state_machine.EndGameState;
import com.java_academy.network.Connector;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class EndGameStateTest {

    private EndGameState endGameState;
    private Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        endGameState = new EndGameState();
    }

    @Test
    public void checkIfItIsEndingState(){
        assertTrue(endGameState.isEndingState());
    }

    @Test
    public void checkIfNextStateIsNull(){
        assertNull(endGameState.changeState(null));
    }
    
    @Test
	public void testDisplay() {
    	endGameState.display(connector::sendMessage);
	}
}
