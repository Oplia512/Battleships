package com.java_academy.logic.attacks;

import com.java_academy.logic.model.BoardManager;
/**
 * @author Bartłomiej Janik
 */
public class NukeAttack implements Attack {

	private BoardManager board;

    public NukeAttack(BoardManager board) {
		this.board = board;
	}

	@Override
    public void attack(int position) {
		board.shotOnIndex(position);
    	System.out.println("Nuke on position: " + position);
    }
}