package com.javaAcademy.logic.jsonModel;

import java.util.Map;

public class MarkedIndexes implements JsonMessage {
	
	private String dataType;
	private Map<Integer, Boolean> map;
	private Boolean isMyBoard;
	
	public MarkedIndexes(String dataType, Map<Integer, Boolean> map) {
		this.dataType = dataType;
		this.map = map;
	}

	public String getDataType() {
		return dataType;
	}

	public Map<Integer, Boolean> getMap() {
		return map;
	}
	
	public void setIsMyBoard(Boolean isMyBoard) {
		this.isMyBoard = isMyBoard;
	}

	public Boolean isMyBoard() {
		return isMyBoard;
	}
}
