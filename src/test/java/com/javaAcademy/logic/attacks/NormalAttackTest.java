package com.javaAcademy.logic.attacks;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaAcademy.logic.attacks.Attack;
import com.javaAcademy.logic.attacks.NormalAttack;
import com.javaAcademy.logic.fleetSettings.FleetBuilder;
import com.javaAcademy.logic.jsonModel.MarkedIndexes;
import com.javaAcademy.logic.model.BoardManager;
import com.javaAcademy.logic.model.Ships;

import static org.testng.Assert.assertEquals;


public class NormalAttackTest {
	
	private BoardManager board;
	
	@BeforeTest
	public void createBoard() {
		Ships ships = FleetBuilder.getNonLocalizedShips();
		board = new BoardManager(ships, 10);
	}
	
	@Test
	public void testNormalAttackGetOneField() {
		Attack attack = new NormalAttack(board);
		MarkedIndexes markedIndexes = attack.attack(23);
		
		assertEquals(markedIndexes.getDataType(), "data");
		assertEquals(markedIndexes.getMap().size(), 1);
	}
	
}
