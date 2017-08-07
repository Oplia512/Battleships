package com.java_academy.logic.json_model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class MessageCreatorTest {
	
	@Test
	public void messageCreatedWithoutPlayerInfo() {
		String jsonMessage = MessageCreator.createJsonMessageByKey("key");

		assertEquals(jsonMessage, "{\"dataType\":\"message\",\"message\":\"key\"}");
	}
	
	@Test
	public void messageCreatedWithPlayerInfo() {
		String jsonMessage = MessageCreator.createJsonMessageByKey("key", "FIRST_PLAYER");
		
		assertEquals(jsonMessage, "{\"dataType\":\"message\",\"message\":\"key\",\"player\":\"FIRST_PLAYER\"}");
	}
	
	@Test
	public void markedIndexesCreatedByMessageCreator() {
		MarkedIndexes mi = new MarkedIndexes("data", null);
		String jsonMsg = MessageCreator.createJsonMarkedIndexes(mi);
		
		assertEquals(jsonMsg, "{\"dataType\":\"data\",\"hitAndSink\":false,\"endOfGame\":false}");
	}

}
