package com.company.AI;

import com.company.GameBoard;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;


public class RandomAITest {
    
    @Test
    public void nextTurnTest(){
        
        RandomAI randomAI = new RandomAI();
        GameBoard gameBoard = new GameBoard();
        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> actual;
        
        for(int i=0; i<expected.size(); i++){
            int position = randomAI.nextTurn(gameBoard);
            gameBoard.assignAIPosition(position);
        }
        actual = gameBoard.getAIOccupiedPositions();
        assertThat(actual,containsInAnyOrder(expected.toArray()));


        gameBoard = new GameBoard(randomAI);
        gameBoard.assignPlayerPosition(1);
        gameBoard.assignPlayerPosition(6);
        gameBoard.assignPlayerPosition(9);
        gameBoard.assignPlayerPosition(4);

        expected = Arrays.asList(2,3,5,7,8);
        for(int i=0; i<expected.size(); i++){
            int position = randomAI.nextTurn(gameBoard);
            gameBoard.assignAIPosition(position);
        }
        actual = gameBoard.getAIOccupiedPositions();
        assertThat(actual,containsInAnyOrder(expected.toArray()));
        
    }
    
}
