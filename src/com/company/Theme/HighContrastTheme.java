package com.company.Theme;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.List;

public class HighContrastTheme extends Theme{

    private final String backgroundColor;
    private final String lineColor ;

    public HighContrastTheme(){
        backgroundColor = "#D3D3D3"; //dark gray
        lineColor = "#696969"; // light gray
    }

    @Override
    public void setButtonBackground(String playerType, Button button) {
        button.setGraphic(null);
        if(playerType.equals("Player")){
            setButtonForPlayer(button);
        }else if(playerType.equals("AI")){
            setButtonForAI(button);
        }
    }

    @Override
    public void setButtonBackground(String playerType, List<Button> buttonList) {
        for(Button button: buttonList ){
            setButtonBackground(playerType,button);
        }
    }

    private void setButtonForPlayer(Button button) {

        button.setStyle("-fx-background-color:" + "#000000");
    }

    private void setButtonForAI(Button button) {

        button.setStyle("-fx-background-color:" + "#FFFFFF");
    }

    @Override
    public void setBoardBackground(Pane pane) {
        pane.setStyle("-fx-background-color: " + backgroundColor);
    }

    @Override
    public void setLineColor(List<Line> lineList) {
        for(Line line : lineList){
            setLineColor(line);
        }
    }

    @Override
    public void setLineColor(Line line) {
        line.setStroke(Color.valueOf(lineColor));
    }
}
