package com.company.AI;

import com.company.GameBoard;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DefensiveAITest {

    @Test
    public void nextTurn(){

        GameBoard gameBoard = new GameBoard();
        DefensiveAI defAI = new DefensiveAI();

        int actualTurn, expectedTurn;

        // player: 1,4   AI:5  expTurn= 7
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignAIPosition(5);
        gameBoard.assignPlayerPosition(4);
        expectedTurn = 7;
        actualTurn = defAI.nextTurn(gameBoard);
        assertEquals(expectedTurn,actualTurn);

        //Player:2,4 AI =3,6 expTurn = 9
        gameBoard = new GameBoard(new DefensiveAI());
        gameBoard.assignPlayerPosition(2);
        gameBoard.assignAIPosition(3);
        gameBoard.assignPlayerPosition(4);
        gameBoard.assignAIPosition(6);
        expectedTurn = 9;
        actualTurn = defAI.nextTurn(gameBoard);
        assertEquals(expectedTurn,actualTurn);

    }

}
