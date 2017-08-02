package com.javaAcademy.logic.tools;

import com.google.gson.Gson;
import com.javaAcademy.logic.jsonModel.JsonMessage;
import com.javaAcademy.logic.jsonModel.MarkedIndexes;
import com.javaAcademy.logic.jsonModel.Message;

public class JsonParser {
	
	private static Gson gson = new Gson();
	
	public static String parseMarkedIndexesToJson(MarkedIndexes mi) {
		return gson.toJson(mi);
	}
	
	public static MarkedIndexes parseMarkedIndexesFromJson(String jsonString) {
		return gson.fromJson(jsonString, MarkedIndexes.class);
	}
	
	public static String parseMessageToJson(Message message) {
		return gson.toJson(message);
	}
	
	public static Message parseMessageFromJson(String jsonString) {
		return gson.fromJson(jsonString, Message.class);
	}
	
	public static JsonMessage decide(String message) {
		if(message.contains("{\"data_type\":\"message")) {
			return JsonParser.parseMessageFromJson(message);
		} else {
			return JsonParser.parseMarkedIndexesFromJson(message);
		}
	}
}
