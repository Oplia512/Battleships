package com.java_academy.logic.jsonModel;

import com.java_academy.logic.tools.JsonParser;

public class MessageCreator {
	
	public static String createJsonMessageByKey(String key) {
		return JsonParser.parseMessageToJson(new Message("message", key));
	}
	
	public static String createJsonMarkedIndexes(MarkedIndexes mi) {
		return JsonParser.parseMarkedIndexesToJson(mi);
	}
}
