package com.java_academy.logic.json_model;

import java.util.Map;

public class MarkedIndexes implements JsonMessage {
	
	private final String dataType;
	private final Map<Integer, Boolean> map;
	private Boolean isMyBoard;
	private Boolean isNukeAvailable;
	private Boolean hitAndSink = false;
	private Boolean endOfGame = false;

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
	
	public Boolean getIsNukeAvailable() {
		return isNukeAvailable;
	}

	public void setIsNukeAvailable(Boolean isNukeAvailable) {
		this.isNukeAvailable = isNukeAvailable;
	}

	public Boolean getHitAndSink() {
		return hitAndSink;
	}

	public void setHitAndSink(Boolean hitAndSink) {
		this.hitAndSink = hitAndSink;
	}

	public Boolean getEndOfGame() {
		return endOfGame;
	}

	public void setEndOfGame(Boolean endOfGame) {
		this.endOfGame = endOfGame;
	}
}
