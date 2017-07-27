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

        Ship ship_one = new Ship(ShipsType.TWO_CELLS).setShipPosition(14, Direction.VERTICAL);

        Ship ship_two = new Ship(ShipsType.THREE_CELLS).setShipPosition(15, Direction.VERTICAL);
        PlayerBoard playerBoard = new PlayerBoard(Players.FIRST_PLAYER, 10);

        SafeZoneCreator creator = new SafeZoneCreator(playerBoard);


        if (creator.create(ship_one)){
            playerBoard.addShip(ship_one);
        }
        printBoard(playerBoard);
        System.out.println();

        if (creator.create(ship_two)){
            playerBoard.addShip(ship_two);
        }
        printBoard(playerBoard);
        System.out.println();
    }

    static void printBoard(final PlayerBoard board) {
        board.getBoard().forEach((key, value) -> {
            if (key % 10 == 0 && key != 0) {
                System.out.println();
            }
            System.out.print(value.getMark() + " ");
        });

        System.out.println();
        System.out.println("--------------------------------");
    }
}
