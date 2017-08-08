package com.java_academy.logic.model;

import com.java_academy.logic.tools.BSLog;

import java.util.List;

public class Ships {

	private int amountAlivedShips;
	private final List<Ship> ships;
	
	public Ships(List<Ship> ships) {
		this.ships = ships;
		amountAlivedShips = ships.size();
	}

	public List<Ship> getFleet() {
		return ships;
	}
	
	boolean isNukeAvailable() {
		for(Ship ship: ships) {
			if(ship.isFourMast() && ship.isAlive()) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		Boolean flag = true;
		for(Ship ship: ships) {
			if(ship.isAlive()) {
				flag = false;
			}
		}
		return flag;
	}
	
	public void setShipCellStateByIndex(Integer index) {
		for(Ship ship: ships) {
			if(ship.containsIndex(index)) {
				ship.setIndex(index, Cell.SHIP_HITTED);
			}
		}
	}
	
	public boolean hitAndSink() {
		int amountOfAlivedShips = 0;
		for(Ship ship: ships) {
			if(ship.isAlive()) {
				BSLog.info(BSLog.getLogger(getClass()), ship.toString());
				amountOfAlivedShips++;
			}
		}
		boolean result = amountOfAlivedShips != amountAlivedShips;
		if(result) {
			amountAlivedShips--;
		}
		return result;
	}
}
