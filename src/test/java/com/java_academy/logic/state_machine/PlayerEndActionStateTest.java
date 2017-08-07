package com.java_academy.logic.state_machine;

import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.PlayerEndActionState;
import com.java_academy.network.Connector;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class PlayerEndActionStateTest {

    private PlayerEndActionState playerEndActionState;
    private Players player1;
    private Players player2;
    private MarkedIndexes mi;
    private Map<Integer, Boolean> map;
    private Connector connector = mock(Connector.class);

    @BeforeTest
    public void setUp(){
        player1 = Players.FIRST_PLAYER;
        player2 = Players.SECOND_PLAYER;
        
        player1.getPlayer().createFleet();
        player2.getPlayer().createFleet();
        
        map = new HashMap<>();
        map.put(1, true);
        mi = new MarkedIndexes("data", map);
        playerEndActionState = new PlayerEndActionState(player1, mi);
    }

	@Test
    public void checkIfItIsEndingState(){
        assertFalse(playerEndActionState.isEndingState());
    }
	
	@Test
	public void checkIfSomeoneWonShouldBeTrue() {
		for(int i = 0; i < 100; i++) {
			player2.getPlayer().getBoard().shotOnIndex(i);
		}
		assertTrue(playerEndActionState.checkIfPlayerWon(player1));
	}
	
	@Test
	public void somethingWasHitIsTrue() {
		assertTrue(playerEndActionState.somethingWasHit(map));
	}
	
	@Test
	public void testDisplay() {
		playerEndActionState.display(connector::sendMessage);
	}
	
}
