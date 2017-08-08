package com.java_academy.logic.tools;

import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.json_model.Message;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JsonParserTest {
	
	@Test
	public void parseMarkedIndexesToJsonTest() {
		Map<Integer, Boolean> nukeOnZeroField = new HashMap<>();
		nukeOnZeroField.put(0, true);
		nukeOnZeroField.put(1, false);
		nukeOnZeroField.put(10, true);
		nukeOnZeroField.put(11, false);
		MarkedIndexes mi = new MarkedIndexes("data", nukeOnZeroField);
		
		String indexes = JsonParser.parseMarkedIndexesToJson(mi);
		
		assertEquals(indexes, "{\"dataType\":\"data\",\"map\":{\"0\":true,\"1\":false,\"10\":true,\"11\":false},\"hitAndSink\":false,\"endOfGame\":false}");
	}
	
	@Test
	public void parseMarkedIndexesFromJsonTest() {
		String jsonString = "{\"dataType\":\"data\",\"map\":{\"0\":true,\"1\":false,\"10\":true,\"11\":false},\"hitAndSink\":false}";
		
		assertTrue(JsonParser.parseMarkedIndexesFromJson(jsonString) != null);
	}
	
	@Test
	public void parseMessageToJsonTest() {
		Message message = new Message("message", "This is message");
		Message messageWithPlayer = new Message("message", "hello world", "FIRST_PLAYER");
		
		String jsonMessage = JsonParser.parseMessageToJson(message);
		String jsonMessageWithPlayerInfo = JsonParser.parseMessageToJson(messageWithPlayer);

		assertEquals(jsonMessage, "{\"dataType\":\"message\",\"message\":\"This is message\"}");
		assertEquals(jsonMessageWithPlayerInfo, "{\"dataType\":\"message\",\"message\":\"hello world\",\"player\":\"FIRST_PLAYER\"}");
	}
	
	@Test
	public void parseMessagesWithoutPlayerInfoFromJsonTest() {
		String jsonString = "{\"dataType\":\"message\",\"message\":\"hello world\"}";
		
		assertTrue(JsonParser.parseMessageFromJson(jsonString) != null);
	}
	
	@Test
	public void parseMessagesWithPlayerInfoFromJsonTest() {
		String jsonString = "{\"dataType\":\"message\",\"message\":\"hello world\",\"player\":\"FIRST_PLAYER\"}";
		
		assertTrue(JsonParser.parseMessageFromJson(jsonString) != null);
	}
	
	@Test
	public void shouldBeMessageInDecideMethod() {
		String jsonString = "{\"dataType\":\"message\",\"message\":\"hello world\",\"player\":\"FIRST_PLAYER\"}";
		
		assertTrue(JsonParser.decide(jsonString) instanceof Message);
	}
	
	@Test
	public void shouldBeMarkedIndexesInDecideMethod() {
		String jsonString = "{\"dataType\":\"data\",\"map\":{\"0\":true,\"1\":false,\"10\":true,\"11\":false},\"hitAndSink\":false}";
		
		assertTrue(JsonParser.decide(jsonString) instanceof MarkedIndexes);
	}
}
