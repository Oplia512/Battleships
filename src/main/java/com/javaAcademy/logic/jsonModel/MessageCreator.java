package com.javaAcademy.logic.jsonModel;

import com.javaAcademy.logic.tools.JsonParser;

public class MessageCreator {
	
	public static String createJsonMessageByKey(String key) {
		return JsonParser.parseMessageToJson(new Message("message", key));
	}
	
	public static String createJsonMarkedIndexes(MarkedIndexes mi) {
		return JsonParser.parseMarkedIndexesToJson(mi);
	}
}
