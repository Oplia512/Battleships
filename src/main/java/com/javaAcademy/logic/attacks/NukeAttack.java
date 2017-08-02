package com.javaAcademy.logic.attacks;

import java.util.List;

import com.javaAcademy.logic.jsonModel.MarkedIndexes;
import com.javaAcademy.logic.model.BoardManager;
/**
 * @author Bartłomiej Janik
 */
public class NukeAttack implements Attack {

	private final BoardManager board;

    public NukeAttack(BoardManager board) {
		this.board = board;
	}

	@Override
	public MarkedIndexes attack(Integer index) {
		List<Integer> neighbours = board.getNeighboursForPoint(index);
		neighbours.add(index);
		for(Integer shotIndex: neighbours) {
			board.shotOnIndex(shotIndex);
		}
		return new MarkedIndexes("data", board.getCellChangesByIndexes(neighbours));
	}
}