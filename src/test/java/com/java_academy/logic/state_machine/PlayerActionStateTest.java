package com.java_academy.logic.state_machine;

import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.model.BoardManager;
import com.java_academy.logic.model.Players;
import com.java_academy.network.Connector;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class PlayerActionStateTest {

    private PlayerActionState playerActionState;
    private BoardManager board;
    private final Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        Players player1 = Players.FIRST_PLAYER;
        Players player2 = Players.SECOND_PLAYER;
        
        player1.getPlayer().createFleet();
        player2.getPlayer().createFleet();
        
        playerActionState = new PlayerActionState(player1);
        board = new BoardManager(null, 10);
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(playerActionState.isEndingState());
    }
    
    @Test
    public void dropNukeGiveNineIndexes() {
    	String coord = "23";
    	MarkedIndexes resultMI = playerActionState.dropNuke(coord, board);
    	
    	assertEquals(resultMI.getMap().size(), 9);
    }
    
    @Test
    public void shotOnFieldGiveNineIndexes() {
    	String coord = "23";
    	MarkedIndexes resultMI = playerActionState.shotOnField(coord, board);
    	
    	assertEquals(resultMI.getMap().size(), 1);
    }
    
    @Test
    public void nextStateIsPlayerEndActionState() {
    	assertTrue(playerActionState.changeState("12") instanceof PlayerEndActionState);
    	assertTrue(playerActionState.changeState("n23") instanceof PlayerEndActionState);
    }

    @Test
	public void testDisplay() {
    	playerActionState.display(connector::sendMessage);
	}
}
