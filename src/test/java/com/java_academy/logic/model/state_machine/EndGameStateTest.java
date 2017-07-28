package com.java_academy.logic.model.state_machine;

import com.java_academy.logic.state_machine.EndGameState;
import com.java_academy.logic.state_machine.core.GameState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.*;

/**
 * Created by Bartłomiej Janik on 7/27/2017.
 */
@Test
public class EndGameStateTest {

    private GameState endGameState;
    private Supplier<String> supplier;

    @BeforeTest
    public void setUp() {
        endGameState = new EndGameState();
        supplier = () -> "Quit";
    }

    @Test
    public void isThisEndingState() {
        assertTrue(endGameState.isEndingState());
    }

    @Test
    public void isNextGameStateNull() {
        assertEquals(endGameState.changeState(supplier), null);
    }


}
