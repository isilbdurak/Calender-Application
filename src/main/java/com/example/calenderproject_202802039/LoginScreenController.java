package com.example.calenderproject_202802039;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginScreen;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    @FXML
    private Label loginMessageLabel;


    @FXML
    void loginSystemFunction() {

        try {
            Alert alert;
            Connection connection = DbConnection.getConnection();
            String query = "SELECT * FROM Users WHERE UserUsername = ? AND UserPassword = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usernameField.getText());
            statement.setString(2, passwordField.getText());

            ResultSet result = statement.executeQuery();

            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                if (result.next()) {
                    loginMessageLabel.setText("Giriş işlemi başarılı, hoş geldiniz!");
                    String username = result.getString("UserUsername");
                    String password = result.getString("UserPassword");

                    User user = new User(username, password);
                    UserData.setUser(user);

                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventScreen-view.fxml"));
                            Parent eventViewParent = loader.load();
                            EventViewScreenController controller = loader.getController();
                            controller.setUser(user);

                            Scene newScene = new Scene(eventViewParent);
                            Stage primaryStage = (Stage) loginButton.getScene().getWindow();
                            primaryStage.setScene(newScene);
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    delay.play();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}