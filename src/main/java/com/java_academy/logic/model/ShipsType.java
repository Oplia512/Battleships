package com.java_academy.logic.model;

public enum ShipsType {
    ONE_CELL(1, 4),
    TWO_CELLS(2, 3),
    THREE_CELLS(3, 2),
    FOUR_CELLS(4, 1);

    int size;
    int numberOnTheBoard;

    ShipsType(int size, int numberOnTheBoard) {
        this.size = size;
        this.numberOnTheBoard = numberOnTheBoard;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOnTheBoard() {
        return numberOnTheBoard;
    }
}
