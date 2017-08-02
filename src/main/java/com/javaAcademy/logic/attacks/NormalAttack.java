package com.javaAcademy.logic.attacks;

import java.util.Collections;
import java.util.List;

import com.javaAcademy.logic.jsonModel.MarkedIndexes;
import com.javaAcademy.logic.model.BoardManager;
/**
 * @author Bartłomiej Janik
 */
public class NormalAttack implements Attack {
	
	private final BoardManager board;
	
	public NormalAttack(BoardManager board) {
		this.board = board;
	}
	
    @Override
    public MarkedIndexes attack(Integer index) {
    	board.shotOnIndex(index);
		List<Integer> indexes = Collections.singletonList(index);
		return new MarkedIndexes("data", board.getCellChangesByIndexes(indexes));
    }
}