package com.java_academy;

import com.java_academy.logic.AttackLogic.SafeZoneCreator;
import com.java_academy.logic.AttackLogic.ShipPlaceBuilder;
import com.java_academy.logic.Players;
import com.java_academy.logic.model.Direction;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;
import com.java_academy.logic.model.ShipsType;

import java.util.Scanner;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
public class ShipPlacerApp {
    public static void main(String[] args) {

        Ship ship_one = new Ship(ShipsType.TWO_CELLS);
        Ship ship_two = new Ship(ShipsType.THREE_CELLS);
        Ship ship_tree = new Ship(ShipsType.FOUR_CELLS);
        PlayerBoard playerBoard = new PlayerBoard(Players.FIRST_PLAYER, 10);

        printBoard(playerBoard);
        System.out.println();



        boolean canBePlaced = true;
        Scanner scanner = new Scanner(System.in);

        ShipPlaceBuilder shipOneBuilder =new ShipPlaceBuilder(ship_one, playerBoard);

        while (!ship_one.isPlaced() || canBePlaced){
           canBePlaced = shipOneBuilder.tryToPlaceShip(scanner.nextInt());
        }

        printBoard(playerBoard);
        System.out.println();

        ShipPlaceBuilder shipTwoBuilder =new ShipPlaceBuilder(ship_two, playerBoard);


        canBePlaced = true;
        while (!ship_one.isPlaced() || canBePlaced){
            canBePlaced = shipTwoBuilder.tryToPlaceShip(scanner.nextInt());
        }

        printBoard(playerBoard);
        System.out.println();


        ShipPlaceBuilder shipTreeBuilderr =new ShipPlaceBuilder(ship_tree, playerBoard);

        canBePlaced = true;
        while (!ship_one.isPlaced() || canBePlaced){
            canBePlaced = shipTreeBuilderr.tryToPlaceShip(scanner.nextInt());
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
