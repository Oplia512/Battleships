package com.java_academy.logic.json_model;

import com.java_academy.logic.tools.JsonParser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MarkedIndexesTest {
	
	private MarkedIndexes mi;
	
	@BeforeTest
    public void setUp(){
		mi = new MarkedIndexes("data", null);
		mi.setEndOfGame(true);
		mi.setHitAndSink(true);
		mi.setIsNukeAvailable(true);
		mi.setIsMyBoard(true);
    }
	
	@Test
	public void markedIndexesCreatedByMessageCreator() {
		assertTrue(mi.getEndOfGame());
		assertTrue(mi.getHitAndSink());
		assertTrue(mi.getIsNukeAvailable());
		assertTrue(mi.isMyBoard());
	}
	
	
	@Test
	public void shouldBeMarkedIndexesInDecideMethod() {
		String jsonMarkedIndexes = "{\"dataType\":\"data\",\"isMyBoard\":true,\"isNukeAvailable\":true,\"hitAndSink\":true,\"endOfGame\":true}";
		String jsonMsg = JsonParser.parseMarkedIndexesToJson(mi);
		
		assertEquals(jsonMarkedIndexes, jsonMsg);
	}
	
	

}
