package com.javaAcademy.logic.model;

import org.testng.annotations.Test;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;

import static org.testng.Assert.assertEquals;

public class MessageObjectTest {

	@Test
	public void messageObjectForFirstPlayer() {
		String msg = "Hello World!";
		MessageObject msgObj = new MessageObject(Players.FIRST_PLAYER, msg);
		
		assertEquals(msgObj.getMessage(), msg);
		assertEquals(msgObj.getPlayer(), Players.FIRST_PLAYER);
	}
}
