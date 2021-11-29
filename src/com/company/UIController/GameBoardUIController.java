package com.company.UIController;
import com.company.AI.AI;
import com.company.GameBoard;
import com.company.Theme.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoardUIController implements Initializable {

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;
    @FXML private Button button6;
    @FXML private Button button7;
    @FXML private Button button8;
    @FXML private Button button9;
    @FXML private Line horizontalMatch1;
    @FXML private Line horizontalMatch2;
    @FXML private Line horizontalMatch3;
    @FXML private Line verticalMatch1;
    @FXML private Line verticalMatch2;
    @FXML private Line verticalMatch3;
    @FXML private Line cornerMatch1;
    @FXML private Line cornerMatch2;
    @FXML private Line mainHorizontalLine1;
    @FXML private Line mainHorizontalLine2;
    @FXML private Line mainVerticalLine1;
    @FXML private Line mainVerticalLine2;
    @FXML private Label resultLabel;
    @FXML private AnchorPane boardPane;

    private GameBoard gameBoard;
    private Theme currentTheme;
    private boolean resultPublished = false;
    private final List<Button> buttonList = new ArrayList<>();
    private final List<Button> AIButtonList = new ArrayList<>();
    private final List<Button> playerButtonList = new ArrayList<>();
    private final List<Line> lineList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonList();
        setLineList();
    }

    public void setAI(AI bot){
        gameBoard = new GameBoard(bot);
    }

    private void setLineList() {
        lineList.add(horizontalMatch1);
        lineList.add(horizontalMatch2);
        lineList.add(horizontalMatch3);
        lineList.add(verticalMatch1);
        lineList.add(verticalMatch2);
        lineList.add(verticalMatch3);
        lineList.add(cornerMatch1);
        lineList.add(cornerMatch2);
        lineList.add(mainHorizontalLine1);
        lineList.add(mainHorizontalLine2);
        lineList.add(mainVerticalLine1);
        lineList.add(mainVerticalLine2);
    }
    private void setButtonList() {
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
    }

    public void boardButtonEventHandle(ActionEvent event)  {
        if(isGameRunning()) {
            Button clickedButton = (Button) event.getSource();
            assignPlayerMove(clickedButton);
        }
        if(isGameRunning()) {
            assignAIMove();
        }
        isGameRunning();
    }

    private void assignPlayerMove(Button clickedButton){
        gameBoard.assignPlayerPosition(Integer.parseInt(clickedButton.getId()));
        currentTheme.setButtonBackground("Player",clickedButton);
        playerButtonList.add(clickedButton);
    }

    private void assignAIMove(){
        Button button = buttonList.get(gameBoard.assignAIPosition()-1);
        currentTheme.setButtonBackground("AI",button);
        AIButtonList.add(button);
    }


    private boolean isGameRunning(){
        if(gameBoard.isGameEnded()){
            if(!resultPublished) {
                publishResult(gameBoard.getResult());
            }
            return false;
        }
        return true;
    }

    private void publishResult(String result) {
        if(resultLabel.getText() != null) {
            resultLabel.setText(result);
            resultPublished = true;
            if(!result.equals("Draw")) {
                setWinningLine(gameBoard.getWinningLinePosition());
            }
        }
    }

    private void setWinningLine(int position){ lineList.get(position).setVisible(true); }

    public void setCurrentTheme(Theme theme) {
        this.currentTheme = theme;
        currentTheme.setBoardBackground(boardPane);
        currentTheme.setLineColor(lineList);
        currentTheme.setButtonBackground("Player",playerButtonList);
        currentTheme.setButtonBackground("AI",AIButtonList);
    }


}
