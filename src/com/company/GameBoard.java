package com.company;
import com.company.AI.AI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private AI ai;
    private final List<List<Integer>> possibleWinningStats = new ArrayList<>(9);
    private final List<Integer> freePositions = new ArrayList<>(9);
    private final List<Integer> playerOccupiedPositions = new ArrayList<>(9);
    private final List<Integer> AIOccupiedPositions = new ArrayList<>(9);
    private int winningLinePosition;
    private int turn;
    private String result;

    public GameBoard(AI ai){
        this.ai = ai;
        turn = 0;
        setFreePositions();
        setWiningStats();
    }

    public GameBoard(){
        turn = 0;
        setFreePositions();
        setWiningStats();
    }

    public List<Integer> getPlayerOccupiedPositions() {
        return playerOccupiedPositions;
    }
    public List<Integer> getFreePositions(){ return freePositions; }
    public List<Integer> getAIOccupiedPositions() {
        return AIOccupiedPositions;
    }

    private void setFreePositions() {
        freePositions.add(1);
        freePositions.add(2);
        freePositions.add(3);
        freePositions.add(4);
        freePositions.add(5);
        freePositions.add(6);
        freePositions.add(7);
        freePositions.add(8);
        freePositions.add(9);
    }
    private void setWiningStats() {
        List<Integer> row1 = Arrays.asList(1,2,3);
        List<Integer> row2 = Arrays.asList(4,5,6);
        List<Integer> row3 = Arrays.asList(7,8,9);
        List<Integer> column1 = Arrays.asList(1,4,7);
        List<Integer> column2 = Arrays.asList(2,5,8);
        List<Integer> column3 = Arrays.asList(3,6,9);
        List<Integer> diagonal1 = Arrays.asList(1,5,9);
        List<Integer> diagonal2 = Arrays.asList(3,5,7);

        possibleWinningStats.add(row1);
        possibleWinningStats.add(row2);
        possibleWinningStats.add(row3);
        possibleWinningStats.add(column1);
        possibleWinningStats.add(column2);
        possibleWinningStats.add(column3);
        possibleWinningStats.add(diagonal1);
        possibleWinningStats.add(diagonal2);
    }

    //gets position from AI
    public int assignAIPosition() {
        int position = -1;
        if(ai != null) {
            position = ai.nextTurn(this);
            assignAIPosition(position);
        }
        return position;
    }

    //gets position as a parameter
    public void assignAIPosition(int position){
        occupyFreePosition(position);
        if(!AIOccupiedPositions.contains(position)) {
            AIOccupiedPositions.add(position);
        }
    }

    //gets position from player input
    public void assignPlayerPosition(int position){
        occupyFreePosition(position);
        if(!playerOccupiedPositions.contains(position)) {
            playerOccupiedPositions.add(position);
        }
    }

    private void occupyFreePosition(int position){
        if (freePositions.contains(position)) {
            freePositions.remove(freePositions.indexOf(position));
        }
        turn++;
    }

    public boolean isGameEnded(){

        if(result != null){
            return true;
        }

        if(turn >= 5) {
            //checks for player inputs
            if (isWinningPositionsMatchedWith(playerOccupiedPositions)) {
                result = "Player Wins";
                return true;
            }
            //checks for AI inputs
            if (isWinningPositionsMatchedWith(AIOccupiedPositions)) {
                result = "AI Wins";
                return true;
            }
        }

        //draw condition
        if (turn >= 9 && result == null) {
            result = "Draw";
            return true;
        }

        return false;
    }

    public String getResult(){
        if(isGameEnded()) {
            return result;
        }
        return null;
    }


    public boolean isWinningPositionsMatchedWith(List<Integer> positionList) {
        for (List<Integer> positions : possibleWinningStats) {
            if (positionList.containsAll(positions)) {
                winningLinePosition = possibleWinningStats.indexOf(positions);
                return true;
            }
        }
        return false;
    }

    public int getWinningLinePosition(){
        return winningLinePosition;
    }


}
