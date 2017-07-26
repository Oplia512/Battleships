package com.java_academy.logic.model;

import java.util.ArrayList;
import java.util.List;


public class Ship {

    private int startCell;
    private Direction direction;
    private ShipsType type;

    public Ship(ShipsType type) {
        this.type = type;
    }

    public Ship setShipPosition(int startPoint, Direction direction) {
        this.startCell = startPoint;
        this.direction = direction;
        return this;
    }

    public List<Integer> getShipPosition() {
        final List<Integer> positions = new ArrayList<>();
        int currentPos = startCell;
        for (int i = 0; i < type.getSize(); i++) {
            if (i != 0) {
                currentPos = nextPoint(currentPos, direction);
            }
            positions.add(currentPos);
        }
        return positions;
    }

    private int nextPoint(int point, Direction direction) {
        switch (direction) {
            case VERTICAL:
                point += 10;
                break;
            case HORIZONTAL:
                point += 1;
                break;
        }
        return point;
    }

    public ShipsType getType() {
        return type;
    }
}
