package com.java_academy.logic.jsonModel;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class MessageTest {
	
	@Test
	public void test() {
		String msgForPlayer = "message for player";
		String dataType = "data";
				
		Message msg = new Message(dataType, msgForPlayer);
		
		assertEquals(msg.getDataType(), dataType);
		assertEquals(msg.getMessage(), msgForPlayer);
	}
}
