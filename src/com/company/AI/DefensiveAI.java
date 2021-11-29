package com.company.AI;
import com.company.GameBoard;

import java.util.List;

public  class DefensiveAI implements AI {

    private final RandomAI randomAI;
    private GameBoard gameBoard;
    private int bestMove;

    public DefensiveAI(){
        randomAI = new RandomAI();
    }

    @Override
    public int nextTurn(GameBoard gameBoard) {

        this.gameBoard = gameBoard;

        if(getWinningMoveForAI(gameBoard) || getWinningMoveForPlayer(gameBoard)){
            return bestMove;
        }else{
            return randomAI.nextTurn(gameBoard);
        }
    }

    private boolean getWinningMove(List<Integer> freePositions,List<Integer> occupiedPositions){

        for(int freePosition: freePositions){
            occupiedPositions.add(freePosition);
            if(gameBoard.isWinningPositionsMatchedWith(occupiedPositions)){
                occupiedPositions.remove(occupiedPositions.indexOf(freePosition));
                bestMove = freePosition;
                return true;
            }
            occupiedPositions.remove(occupiedPositions.indexOf(freePosition));
        }

        return false;
    }

    private boolean getWinningMoveForAI(GameBoard gameBoard){
        return getWinningMove(gameBoard.getFreePositions(), gameBoard.getAIOccupiedPositions());
    }

    private boolean getWinningMoveForPlayer(GameBoard gameBoard){
        return getWinningMove(gameBoard.getFreePositions(), gameBoard.getPlayerOccupiedPositions());
    }


}
