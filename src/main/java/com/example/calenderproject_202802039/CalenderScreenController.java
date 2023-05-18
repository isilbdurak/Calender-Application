package com.example.calenderproject_202802039;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CalenderScreenController{
    @FXML
    private Button createEventButton;

    @FXML
    private Button viewEventButton;

    @FXML
    void goToCreateEventScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("eventCreateScreen-view.fxml"));
            Scene newScene = new Scene(root);
            Stage primaryStage = (Stage) createEventButton.getScene().getWindow();
            primaryStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void goToViewEventScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("eventScreen-view.fxml"));
            Scene newScene = new Scene(root);
            Stage primaryStage = (Stage) viewEventButton.getScene().getWindow();
            primaryStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
