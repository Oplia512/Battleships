package com.javaAcademy.logic.stateMachine;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.BattleResultState;
import com.javaAcademy.logic.stateMachine.EndGameState;

/**
 * @author Bartlomiej Janik
 * @since 8/1/2017
 */
@Test
public class BattleResultStateTest {

    private BattleResultState battleResult;
    private Players currentPlayer;

    @BeforeTest
    public void setUp(){
        currentPlayer = Players.FIRST_PLAYER;
        battleResult = new BattleResultState(currentPlayer);
    }

    @Test
    public void checkIfItIsEndingState(){
        Assert.assertFalse(battleResult.isEndingState());
    }

    @Test
    public void checkIfNextStateIsEndGame(){
        Assert.assertTrue(battleResult.changeState(null) instanceof EndGameState);
    }

}
