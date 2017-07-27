package com.java_academy.logic.model;


public enum Cell {
    FREE("O"),
    SHIP("S"),
    UNAVAILABLE("U"),
    SHOT("X"),
    MISSED("-");

    private String mark;

    Cell(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
