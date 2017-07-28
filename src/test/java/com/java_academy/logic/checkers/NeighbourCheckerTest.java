package com.java_academy.logic.checkers;

import com.java_academy.logic.fleet_settings.FleetBuilder;
import com.java_academy.logic.model.BoardManager;
import com.java_academy.logic.model.Ships;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NeighbourCheckerTest {
	
	private BoardManager board;
	private NeighbourChecker neighbourChecker;
	final static Integer xBoardDim = 10;
	
	@BeforeTest
	public void createRandomizer() {
		Ships ships = FleetBuilder.getNonLocalizedShips();
		board = new BoardManager(ships, 10);
		neighbourChecker = new NeighbourChecker(10, board);
	}

	@Test
	public void isNeighbourIfBreakLine() {
		assertFalse(neighbourChecker.isNeighbour(9, 10));
		assertFalse(neighbourChecker.isNeighbour(10, 9));
	}
	
	@Test
	public void isNeighbourTest() {
		assertTrue(neighbourChecker.isNeighbour(23, 12));
		assertTrue(neighbourChecker.isNeighbour(23, 13));
		assertTrue(neighbourChecker.isNeighbour(23, 14));
		assertTrue(neighbourChecker.isNeighbour(23, 22));
		assertTrue(neighbourChecker.isNeighbour(23, 24));
		assertTrue(neighbourChecker.isNeighbour(23, 32));
		assertTrue(neighbourChecker.isNeighbour(23, 33));
		assertTrue(neighbourChecker.isNeighbour(23, 34));
	}
	
	@Test
	public void pointHaveEightNeighbours() {
		assertEquals(neighbourChecker.getNeighboursToCheckForPoint(15).length, 8);
		assertEquals(neighbourChecker.getNeighboursToCheckForPoint(4).length, 8);
	}
}
