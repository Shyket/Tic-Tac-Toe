package com.company.Theme;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ForestTheme extends Theme{

    private final String backgroundColor;
    private final String lineColor ;

    private final String playerImageFilePath = System.getProperty("user.dir").concat("\\src\\com\\company\\resource\\button-apple-icon.png");
    private final String AIImageFilePath = System.getProperty("user.dir").concat("\\src\\com\\company\\resource\\button-clipart-flower.jpg");

    public ForestTheme() {
        backgroundColor = "E3FF00"; //light green
        lineColor = "#006400"; // dark green
    }

    @Override
    public void setBoardBackground(Pane pane) {
        pane.setStyle("-fx-background-color:" + backgroundColor);
    }

    @Override
    public void setButtonBackground(String playerType, Button button) {
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
        ImageView img = getImage(playerImageFilePath);
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(img);
    }

    private void setButtonForAI(Button button) {
        ImageView img = getImage(AIImageFilePath);
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(img);
    }
    private ImageView getImage(String filePath) {

        ImageView img = null;
        try {
            FileInputStream input = new FileInputStream(filePath);
            img = new ImageView(new Image(input));
            img.setFitHeight(50.0);
            img.setFitWidth(50.0);
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return img;
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
