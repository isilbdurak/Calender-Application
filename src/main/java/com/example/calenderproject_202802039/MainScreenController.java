package com.example.calenderproject_202802039;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.IOException;

public class MainScreenController {
    @FXML
    private AnchorPane firstScreen;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    void loginFunction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginScreen-view.fxml"));
            Scene newScene = new Scene(root);
            Stage primaryStage = (Stage) loginButton.getScene().getWindow();
            primaryStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerFunction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registerScreen-view.fxml"));
            Scene newScene = new Scene(root);
            Stage primaryStage = (Stage) registerButton.getScene().getWindow();
            primaryStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}