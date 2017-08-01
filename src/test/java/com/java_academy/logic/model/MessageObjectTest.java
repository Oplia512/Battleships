package com.java_academy.logic.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class MessageObjectTest {

	@Test
	public void messageObjectForFirstPlayer() {
		String msg = "Hello World!";
		MessageObject msgObj = new MessageObject(Players.FIRST_PLAYER, msg);
		
		assertEquals(msgObj.getMessage(), msg);
		assertEquals(msgObj.getPlayer(), Players.FIRST_PLAYER);
	}
}
