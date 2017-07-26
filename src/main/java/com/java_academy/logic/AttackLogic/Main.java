package com.java_academy.logic.AttackLogic;

import com.java_academy.logic.Players;
import com.java_academy.logic.model.Direction;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;
import com.java_academy.logic.model.ShipsType;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
public class Main {
    public static void main(String[] args) {

        Ship ship_one = new Ship(ShipsType.THREE_CELLS).setShipPosition(14, Direction.VERTICAL);

        Ship ship_two = new Ship(ShipsType.THREE_CELLS).setShipPosition(4, Direction.VERTICAL);
        PlayerBoard playerBoard = new PlayerBoard(Players.FIRST_PLAYER, 10);

        SafeZoneCreator.create(ship_one, playerBoard);
        System.out.println();
        SafeZoneCreator.create(ship_two, playerBoard);

    }
}
