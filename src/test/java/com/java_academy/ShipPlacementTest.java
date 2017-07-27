package com.java_academy;

import com.java_academy.logic.AttackLogic.ShipPlaceBuilder;
import com.java_academy.logic.Players;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;
import com.java_academy.logic.model.ShipsType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;

/**
 * Created by Siarhei Shauchenka on 27.07.17.
 */

@Test
public class ShipPlacementTest {


    @DataProvider(name = "shipPlaceProvider")
    public Object[][] provideData(){
        PlayerBoard playerBoard = new PlayerBoard(Players.FIRST_PLAYER, 10);
        return new Object[][]{
                {playerBoard, new Ship(ShipsType.ONE_CELL), new int[]{0}, new boolean[]{true}, true, 1},
                {playerBoard, new Ship(ShipsType.TWO_CELLS), new int[]{0, 1}, new boolean[]{false, false}, false, 2},
                {playerBoard, new Ship(ShipsType.TWO_CELLS), new int[]{8, 18}, new boolean[]{true, true}, true, 3},
                {playerBoard, new Ship(ShipsType.THREE_CELLS), new int[]{20, 21, 31}, new boolean[]{true, true, true}, true, 4},
                {playerBoard, new Ship(ShipsType.FOUR_CELLS), new int[]{50,51, 52, 42}, new boolean[]{true, true, true, false}, false, 5},
                {playerBoard, new Ship(ShipsType.THREE_CELLS), new int[]{90, 91, 92}, new boolean[]{true, true, true}, true, 6},
                {playerBoard, new Ship(ShipsType.THREE_CELLS), new int[]{99,89, 88}, new boolean[]{true, true, true}, true, 7},
                {playerBoard, new Ship(ShipsType.ONE_CELL), new int[]{59}, new boolean[]{true}, true, 8},
                {playerBoard, new Ship(ShipsType.TWO_CELLS), new int[]{70, 73}, new boolean[]{true, false}, false, 9},
        };
    }

    @Test(dataProvider = "shipPlaceProvider")
    public void shipPlacementTest(PlayerBoard playerBoard, Ship ship, int[] places, boolean[] placementResult, boolean shipPlacementResult, int testCase){
        ShipPlaceBuilder shipPlaceBuilder =new ShipPlaceBuilder(ship, playerBoard);

        for (int i = 0; i < ship.getSize(); i++) {
            boolean result = shipPlaceBuilder.tryToPlaceShip(places[i]);
            assertEquals(result, placementResult[i],
                    String.format("test #%d, point %d, expectedresult %s : ",testCase, places[i], placementResult[i]));
        }
        assertEquals(ship.isPlaced(), shipPlacementResult);
        printBoard(playerBoard, testCase);
    }


    private void printBoard(final PlayerBoard board, int testCase) {
        System.out.println("testCase [" + testCase+ "]");
        System.out.println();
        board.getBoard().forEach((key, value) -> {
            if (key % 10 == 0 && key != 0) {
                System.out.println();
            }
            System.out.print(value.getMark() + " ");
        });

        System.out.println();
        System.out.println("--------------------------------");
        System.out.println();
    }

}
