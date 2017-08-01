package com.java_academy.logic.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class PlayersTest {
	
	private final int amountOfPlayers = 2;
	
	@Test
	public void testGetOpponent() {
		assertEquals(Players.FIRST_PLAYER.getOpponent(), Players.SECOND_PLAYER);
	}
	
	@Test
	public void getListOfPlayersTest() {
		assertEquals(Players.values().length, amountOfPlayers);
	}
	
	@Test
	public void getPlayerTest() {
		assertNotNull(Players.FIRST_PLAYER.getPlayer());
	}

}
