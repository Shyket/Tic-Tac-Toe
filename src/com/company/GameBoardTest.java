package com.company;
import com.company.AI.RandomAI;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    public void assignPlayerPosition(){

        GameBoard gameBoard = new GameBoard();
        gameBoard.assignPlayerPosition(4);
        gameBoard.assignPlayerPosition(8);
        gameBoard.assignPlayerPosition(9);

        List<Integer> expectedPlayerPositions = Arrays.asList(4,8,9);
        List<Integer> actualPlayerPositions ;

        actualPlayerPositions = gameBoard.getPlayerOccupiedPositions();
        assertThat(actualPlayerPositions, containsInAnyOrder(expectedPlayerPositions.toArray()));

    }


    @Test
    public void assignAIPosition(){
        GameBoard gameBoard = new GameBoard();

        List<Integer> expectedAIPositions = Arrays.asList(3,5,6,7);
        List<Integer> unexpectedAIPositions = Arrays.asList(4,5,9,7);
        List<Integer> actualAIPositions;

        for(int position : expectedAIPositions){
            gameBoard.assignAIPosition(position);
        }

        actualAIPositions = gameBoard.getAIOccupiedPositions();
        assertThat(actualAIPositions, containsInAnyOrder(expectedAIPositions.toArray()));
        assertThat(actualAIPositions, not(containsInAnyOrder(unexpectedAIPositions.toArray())));
    }

    @Test
    public void isWinningPositionsMatchedWith(){

        GameBoard gameBoard = new GameBoard(new RandomAI());
        List<Integer> playerPositions = Arrays.asList(1,2,4,7,9); //player has 1,4,7 winning position
        List<Integer> AIPositions = Arrays.asList(3,5,6,8); // Ai has no winning position

        assertFalse(gameBoard.isWinningPositionsMatchedWith(AIPositions));
        assertTrue(gameBoard.isWinningPositionsMatchedWith(playerPositions));
    }

    @Test
    public void isGameEnded(){

        GameBoard gameBoard = new GameBoard(new RandomAI());
        assertFalse(gameBoard.isGameEnded());

        gameBoard.assignPlayerPosition(3);
        gameBoard.assignAIPosition(2);
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignAIPosition(5);
        assertFalse(gameBoard.isGameEnded());

        gameBoard.assignPlayerPosition(9);
        gameBoard.assignAIPosition(8);
        assertTrue(gameBoard.isGameEnded()); //now the game should be ended
        gameBoard.assignPlayerPosition(2);
        assertTrue(gameBoard.isGameEnded());

    }

    @Test
    public void getResult(){

        //player: 1,2,4,9  AI: 3,5,6,7
        GameBoard gameBoard = new GameBoard();
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignAIPosition(3);
        gameBoard.assignPlayerPosition(2);
        gameBoard.assignAIPosition(5);
        gameBoard.assignPlayerPosition(4);
        gameBoard.assignAIPosition(6);
        gameBoard.assignPlayerPosition(9);
        gameBoard.assignAIPosition(7);

        String expectedResult = "AI Wins";
        assertEquals(expectedResult,gameBoard.getResult());

        //player: 1,2,8,5  AI: 3,9,4,7
        gameBoard = new GameBoard();
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignAIPosition(3);
        gameBoard.assignPlayerPosition(2);
        gameBoard.assignAIPosition(9);
        gameBoard.assignPlayerPosition(8);
        gameBoard.assignAIPosition(4);
        gameBoard.assignPlayerPosition(5);
        gameBoard.assignAIPosition(7);

        expectedResult = "Player Wins";
        assertEquals(expectedResult,gameBoard.getResult());

        //player: 1,2,8,6  AI: 3,9,4,5
        gameBoard = new GameBoard();
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignAIPosition(3);
        gameBoard.assignPlayerPosition(2);
        gameBoard.assignAIPosition(9);
        gameBoard.assignPlayerPosition(8);
        gameBoard.assignAIPosition(4);
        gameBoard.assignPlayerPosition(6);
        gameBoard.assignAIPosition(5);
        gameBoard.assignPlayerPosition(7);

        expectedResult = "Draw";
        assertEquals(expectedResult,gameBoard.getResult());

    }






}