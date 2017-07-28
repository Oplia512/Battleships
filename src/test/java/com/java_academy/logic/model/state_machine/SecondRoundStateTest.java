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
public class SecondRoundStateTest {

    private GameState secondRoundState;
    private Supplier<String> supplierMiss;
    private Supplier<String> supplierShot;
    private Supplier<String> supplierEndOfGame;

    @BeforeTest
    public void setUp() {
        secondRoundState = new SecondRoundState();
        supplierShot = () -> "shot";
        supplierMiss = () -> "miss";
        supplierEndOfGame = () -> "End of game";
    }

    @Test
    public void isThisEndingState() {
        assertFalse(secondRoundState.isEndingState());
    }

    @Test
    public void returnFirstRoundStateIfMiss() {
        assertTrue(secondRoundState.changeState(supplierMiss) instanceof FirstRoundState);
    }

    @Test
    public void returnSecondRoundStateIfShot() {
        assertTrue(secondRoundState.changeState(supplierShot) instanceof SecondRoundState);
    }

    @Test
    public void returnEndGameStateIfNoMoreShipsAreAlive(){
        assertTrue(secondRoundState.changeState(supplierEndOfGame) instanceof EndGameState);
    }

}
