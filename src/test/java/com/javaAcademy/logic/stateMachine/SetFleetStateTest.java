package com.javaAcademy.logic.stateMachine;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaAcademy.logic.stateMachine.SetFleetState;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
public class SetFleetStateTest {

    private SetFleetState setFleetState;

    @BeforeTest
    public void setUp(){
        setFleetState = new SetFleetState();
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(setFleetState.isEndingState());
    }
}
