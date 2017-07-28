package com.java_academy.logic.model.state_machine;

import com.java_academy.logic.state_machine.EndGameState;
import com.java_academy.logic.state_machine.FirstRoundState;
import com.java_academy.logic.state_machine.SecondRoundState;
import com.java_academy.logic.state_machine.core.GameState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.*;

/**
 * Created by Bartłomiej Janik on 7/28/2017.
 */
@Test
public class FirstRoundStateTest {

    private GameState firstRoundState;
    private Supplier<String> supplierShot;
    private Supplier<String> supplierMiss;
    private Supplier<String> supplierEndOfGame;

    @BeforeTest
    public void setUp() {
        firstRoundState = new FirstRoundState();
        supplierShot = () -> "shot";
        supplierMiss = () -> "miss";
        supplierEndOfGame = () -> "End of game";
    }

    @Test
    public void isThisEndingState() {
        assertFalse(firstRoundState.isEndingState());
    }

    @Test
    public void returnFirstRoundStateIfShot() {
        assertEquals(firstRoundState.changeState(supplierShot), firstRoundState);
    }

    @Test
    public void returnSecondRoundStateIfMiss() {
        assertTrue(firstRoundState.changeState(supplierMiss) instanceof SecondRoundState);
    }

    @Test
    public void returnEndGameStateIfNoMoreShipsAreAlive() {
        assertTrue(firstRoundState.changeState(supplierEndOfGame) instanceof EndGameState);
    }


}
