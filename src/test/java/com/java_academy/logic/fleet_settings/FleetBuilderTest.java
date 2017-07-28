package com.java_academy.logic.fleet_settings;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.java_academy.logic.fleet_settings.FleetBuilder;
import com.java_academy.logic.fleet_settings.ShipSetter;
import com.java_academy.logic.model.BoardManager;
import com.java_academy.logic.model.Ship;
import com.java_academy.logic.model.Ships;

public class FleetBuilderTest {
	private BoardManager board;
	private ShipSetter fleetSetter;
	final static Integer xBoardDim = 10;
	private Ships ships;
	
	@BeforeTest
	public void createRandomizer() {
		ships = FleetBuilder.getNonLocalizedShips();
		board = new BoardManager(ships, 10);
		fleetSetter = new ShipSetter(board);
	}
	
	@Test
	public void getNextIndexUp() {
		assertEquals(fleetSetter.getNextIndex(20, 0), 10);
	}
	
	@Test
	public void getNextIndexRight() {
		assertEquals(fleetSetter.getNextIndex(0, 1), 1);
	}
	
	@Test
	public void getNextIndexDown() {
		assertEquals(fleetSetter.getNextIndex(11, 2), 21);
	}
	
	@Test
	public void getNextIndexLeft() {
		assertEquals(fleetSetter.getNextIndex(25, 3), 24);
	}
	
	@Test
	public void busyNeighbours() {
		BoardManager board = new BoardManager(ships, 10);
		ShipSetter fleetSetter = new ShipSetter(board);
		fleetSetter.setShip(Arrays.asList(9, 19, 29), new Ship(3));
		
		assertTrue(fleetSetter.busyNeighbours(18));
		assertFalse(fleetSetter.busyNeighbours(20));
	}
	
	@Test
	public void testPointIsAvailable() {
		BoardManager board = new BoardManager(ships, 10);
		ShipSetter fleetSetter = new ShipSetter(board);
		
		fleetSetter.setShipOnTheBoard(Arrays.asList(9, 19, 29), new Ship(3));
		fleetSetter.setShipOnTheBoard(Arrays.asList(83, 84, 85, 86), new Ship(4));
		
		assertTrue(fleetSetter.pointIsAvailable(4, 4)); //empty space
		assertTrue(fleetSetter.pointIsAvailable(10, 11)); //new line<index close to ship>
		assertTrue(fleetSetter.pointIsAvailable(49, 39)); //close ship vertically
		assertTrue(fleetSetter.pointIsAvailable(48, 38)); //close ship diagonally
		assertTrue(fleetSetter.pointIsAvailable(63, 64)); //close ship horyzontally
		
		assertFalse(fleetSetter.pointIsAvailable(9, 19)); //on ship
		assertFalse(fleetSetter.pointIsAvailable(94, 95)); //ship neighborhood
		assertFalse(fleetSetter.pointIsAvailable(77, 78)); //ship neighborhood diagonally
	}
}
