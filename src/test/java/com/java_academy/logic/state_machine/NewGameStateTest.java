package com.java_academy.logic.state_machine;

import com.java_academy.network.Connector;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class NewGameStateTest {

    private NewGameState newGameState;
    private final Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        newGameState = new NewGameState();
    }

    @Test
    public void checkIfItIsEndingState(){
        assertFalse(newGameState.isEndingState());
    }

    @Test
    public void  checkIfNextStateIsSetFleetState(){
        assertTrue(newGameState.changeState(null) instanceof GetBoardForPlayer);
    }
    
    @Test
	public void testDisplay() {
    	newGameState.display(connector::sendMessage);
	}
}
