package com.javaAcademy.logic.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.javaAcademy.logic.fleetSettings.FleetBuilder;
import com.javaAcademy.logic.model.Cell;
import com.javaAcademy.logic.model.Ship;
import com.javaAcademy.logic.model.Ships;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShipsTest {
	
	private Ships shipsFromBuilder;
	private Ships ships;
	private Ship fourMastShip;
	private Ship twoMastShip;
	
	private int mast4_1 = 0;
	private int mast4_2 = 10;
	private int mast4_3 = 20;
	private int mast4_4 = 30;
	
	private int firstIndexTwoMastShip = 4;
	private int secondIndexTwoMastShip = 5;
	
	
	@BeforeMethod
	public void createFleet() {
		shipsFromBuilder = FleetBuilder.getNonLocalizedShips();
		
		fourMastShip = new Ship(4);
		fourMastShip.setIndex(mast4_1, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_2, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_3, Cell.SHIP_ALIVE);
		fourMastShip.setIndex(mast4_4, Cell.SHIP_ALIVE);
		twoMastShip = new Ship(2);
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
	
}
