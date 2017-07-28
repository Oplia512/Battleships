package com.java_academy.logic.model;


public enum Cell {
    FREE("*"),
    SHIP("O"),
    UNAVAILABLE("-"),
    SHOT("X"),
    MISSED("M");

    private String mark;

    Cell(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
