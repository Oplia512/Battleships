package com.java_academy.logic.model;

import com.java_academy.logic.fleet_settings.FleetBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShipsTest {
	
	private Ships shipsFromBuilder;
	private Ships ships;

    private final int mast4_1 = 0;
	private final int mast4_2 = 10;
	private final int mast4_3 = 20;
	private final int mast4_4 = 30;
	
	private final int firstIndexTwoMastShip = 4;
	private final int secondIndexTwoMastShip = 5;
	
	
	@BeforeMethod
	public void createFleet() {
		shipsFromBuilder = FleetBuilder.getNonLocalizedShips();

        Ship fourMastShip = new Ship(4);
		fourMastShip.setIndex(mast4_1, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_2, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_3, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_4, Cell.SHIP_ALIVE);
        Ship twoMastShip = new Ship(2);
		twoMastShip.setIndex(firstIndexTwoMastShip, Cell.SHIP_ALIVE);
		twoMastShip.setIndex(secondIndexTwoMastShip, Cell.SHIP_ALIVE);
		
		List<Ship> shipsList = new ArrayList<>();
		shipsList.add(fourMastShip);
		shipsList.add(twoMastShip);
		ships = new Ships(shipsList);
	}
	
	@Test
	public void testAmountOfShipsInFleet() {
		assertEquals(shipsFromBuilder.getFleet().size(), 10);
	}
	
	@Test
	public void nukeAvailableBeforeAttack() {

		assertTrue(ships.isNukeAvailable());
		
		ships.setShipCellStateByIndex(mast4_1);
		ships.setShipCellStateByIndex(mast4_2);
		ships.setShipCellStateByIndex(mast4_3);
		ships.setShipCellStateByIndex(mast4_4);
		
		assertFalse(ships.isNukeAvailable());
	}
	
	@Test
	public void fleetEmptyAfterAttacks() {
		ships.setShipCellStateByIndex(mast4_1);
		ships.setShipCellStateByIndex(mast4_2);
		ships.setShipCellStateByIndex(mast4_3);
		ships.setShipCellStateByIndex(mast4_4);
		
		assertFalse(ships.isEmpty());
		
		ships.setShipCellStateByIndex(firstIndexTwoMastShip);
		ships.setShipCellStateByIndex(secondIndexTwoMastShip);
		
		assertTrue(ships.isEmpty());
	}
	
	@Test
	public void hitAndSink() {
		ships.setShipCellStateByIndex(mast4_1);
		assertFalse(ships.hitAndSink());
		
		ships.setShipCellStateByIndex(mast4_2);
		assertFalse(ships.hitAndSink());
		
		ships.setShipCellStateByIndex(mast4_3);
		assertFalse(ships.hitAndSink());
		
		ships.setShipCellStateByIndex(mast4_4);
		assertTrue(ships.hitAndSink());
		
		
		ships.setShipCellStateByIndex(firstIndexTwoMastShip);
		assertFalse(ships.hitAndSink());
		
		ships.setShipCellStateByIndex(secondIndexTwoMastShip);
		assertTrue(ships.hitAndSink());
	}
	
}
