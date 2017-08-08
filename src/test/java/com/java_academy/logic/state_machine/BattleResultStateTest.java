package com.java_academy.logic.state_machine;

import com.java_academy.logic.model.Players;
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
public class BattleResultStateTest {

    private BattleResultState battleResult;
    private final Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        Players currentPlayer = Players.FIRST_PLAYER;
        battleResult = new BattleResultState(currentPlayer);
    }

    @Test
    public void checkIfItIsEndingState(){
        assertFalse(battleResult.isEndingState());
    }

    @Test
    public void checkIfNextStateIsEndGame(){
        assertTrue(battleResult.changeState(null) instanceof EndGameState);
    }
    
    @Test
	public void testDisplay() {
    	battleResult.display(connector::sendMessage);
	}
}
