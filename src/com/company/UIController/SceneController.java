package com.company.UIController;
import com.company.AI.AI;
import com.company.AI.DefensiveAI;
import com.company.AI.RandomAI;
import com.company.Theme.ClassicTheme;
import com.company.Theme.ForestTheme;
import com.company.Theme.HighContrastTheme;
import com.company.Theme.Theme;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class SceneController implements Initializable {

    @FXML private AnchorPane leftPane;
    @FXML private ChoiceBox<String> themeChoiceBox;
    private GameBoardUIController gameBoardUIController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setThemeOptions();
        setNewGameBoard(new RandomAI(),getSelectedTheme(themeChoiceBox.getValue()));
    }

    private void setThemeOptions() {
        List<String> themeOptionList = new ArrayList<>();
        themeOptionList.add("Classic");
        themeOptionList.add("Forest");
        themeOptionList.add("High Contrast");
        themeChoiceBox.getItems().addAll(themeOptionList);
        themeChoiceBox.setValue("Classic");
    }

    public void changeTheme(ActionEvent event){
        if(gameBoardUIController != null){
            gameBoardUIController.setCurrentTheme(getSelectedTheme(themeChoiceBox.getValue()));
        }
    }

    private Theme getSelectedTheme(String selectedTheme) {
        switch (selectedTheme) {
            case "Forest":
                return new ForestTheme();
            case "High Contrast":
                return new HighContrastTheme();
            default:
                return new ClassicTheme();
        }
    }

    public void AIButtonEventHandle(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonID = button.getId();

        if (buttonID.equals("defAI")) {
            setNewGameBoard(new DefensiveAI(), getSelectedTheme(themeChoiceBox.getValue()));
        } else {
            setNewGameBoard(new RandomAI(), getSelectedTheme(themeChoiceBox.getValue()));
        }
    }

    private void setNewGameBoard(AI bot, Theme theme) {
        try {
            FileInputStream f=  new FileInputStream(
                    new File(System
                            .getProperty("user.dir")
                            .concat("\\src\\com\\company\\UI\\" +
                                    "Board.fxml")));

            FXMLLoader loader = new FXMLLoader();
            AnchorPane gameBoardPane = loader.load(f);

            leftPane.getChildren().setAll(gameBoardPane);
            gameBoardUIController = loader.getController();
            gameBoardUIController.setAI(bot);
            gameBoardUIController.setCurrentTheme(theme);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
