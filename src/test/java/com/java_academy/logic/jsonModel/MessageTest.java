package com.java_academy.logic.jsonModel;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
