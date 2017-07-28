package com.java_academy;

import com.java_academy.logic.AttackLogic.Randomizer;
import com.java_academy.logic.Players;
import com.java_academy.logic.model.PlayerBoard;
import com.java_academy.logic.model.Ship;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Siarhei Shauchenka on 27.07.17.
 */

@Test
public class RandomizerTest {

    public void createRandomBoardTest(){
        PlayerBoard playerBoard = new PlayerBoard(Players.FIRST_PLAYER, 10);
        Randomizer randomizer = new Randomizer(playerBoard);

        for (Ship ship: Players.FIRST_PLAYER.getShipList()) {
            randomizer.setShip(ship);
            assertEquals(ship.isPlaced(), true);
            printBoard(playerBoard, 1);
        }


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
