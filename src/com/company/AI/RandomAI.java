package com.company.AI;
import com.company.GameBoard;

import java.util.List;
import java.util.Random;

public class RandomAI implements AI {

    Random random;
    public RandomAI(){
        random = new Random();
    }

    //find out a random free space between 1-9 in the gamBoard
    @Override
    public int nextTurn(GameBoard gameBoard) {

        List<Integer> freeSpaces = gameBoard.getFreePositions();
        int pos = random.nextInt(9)+1;

        while(!freeSpaces.contains(pos)){
            pos = random.nextInt(9)+1;
        }

        return pos;
    }
}
