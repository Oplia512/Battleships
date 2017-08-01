package com.java_academy.logic.attacks;

import java.util.Arrays;
import java.util.List;

import com.java_academy.logic.jsonModel.MarkedIndexes;
import com.java_academy.logic.model.BoardManager;
/**
 * @author Bartłomiej Janik
 */
public class NormalAttack implements Attack {
	
	private BoardManager board;
	
	public NormalAttack(BoardManager board) {
		this.board = board;
	}
	
    @Override
    public MarkedIndexes attack(Integer index) {
    	board.shotOnIndex(index);
		List<Integer> indexes = Arrays.asList(new Integer[]{index});
		return new MarkedIndexes("data", board.getCellChangesByIndexes(indexes));
    }
}