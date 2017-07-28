package com.java_academy.logic.model.state_machine;

import com.java_academy.logic.state_machine.FirstRoundState;
import com.java_academy.logic.state_machine.NewGameState;
import com.java_academy.logic.state_machine.core.GameState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.*;

/**
 * Created by Bartłomiej Janik on 7/28/2017.
 */
@Test
public class NewGameStateTest {

    private GameState newGameStateTest;
    private Supplier<String> supplier;

    @BeforeTest
    public void setUp(){
        newGameStateTest = new NewGameState();
        supplier = () -> "msg";
    }

    @Test
    public void isThisEndingState(){
        assertFalse(newGameStateTest.isEndingState());
    }

    public void returnFirstRoundState(){
        assertTrue(newGameStateTest.changeState(supplier) instanceof FirstRoundState);
    }



}
