package com.java_academy.logic;

public enum Players {
    FIRST_PLAYER("first"),
    SECOND_PLAYER("second"),;

    private String name;

    Players(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
