package com.javaAcademy.logic.stateMachine;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaAcademy.logic.stateMachine.NewGameState;
import com.javaAcademy.logic.stateMachine.SetFleetState;


/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class NewGameStateTest {

    private NewGameState newGameState;

    @BeforeTest
    public void setUp(){
        newGameState = new NewGameState();
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(newGameState.isEndingState());
    }

    @Test
    public void  checkIfNextStateIsSetFleetState(){
        Assert.assertTrue(newGameState.changeState(null) instanceof SetFleetState);
    }

}
