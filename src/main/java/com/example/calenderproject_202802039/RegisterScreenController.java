package com.example.calenderproject_202802039;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegisterScreenController {
    @FXML
    private TextField TcNoField;

    @FXML
    private Button completedButton;

    @FXML
    private TextField mailField;

    @FXML
    private TextField nameSurnameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField userType;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField userIDField;
    @FXML
    private Label registerMessageLabel;

    @FXML
    void completedFunction() {
        String tcNo = TcNoField.getText();
        String mail = mailField.getText();
        String nameSurname = nameSurnameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String usertype = userType.getText();
        String userid = userIDField.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "INSERT INTO users (UserID, UserNameSurname, UserTCNo, UserTelNo, UserEmaıl, UserUsername, UserPassword, UserType) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userid);
            statement.setString(2, nameSurname);
            statement.setString(3, tcNo);
            statement.setString(4, phoneNumber);
            statement.setString(5, mail);
            statement.setString(6, username);
            statement.setString(7, password);
            statement.setString(8, usertype);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        registerMessageLabel.setText("Kaydolma işlemi başarılı, hoş geldiniz!");
        //completedButton.getScene().getWindow().hide();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("calenderScreen-view.fxml"));
                Scene newScene = new Scene(root);
                Stage primaryStage = (Stage) completedButton.getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }
}
