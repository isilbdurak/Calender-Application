package com.example.calenderproject_202802039;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class EventViewScreenController {
    @FXML
    private TextField dateLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField eventDescriptionLabel;

    @FXML
    private TextField eventTypeLabel;

    @FXML
    private TextField finishDateLabel;

    @FXML
    private TextField startDateLabel;

    @FXML
    private Button updateButton;

    @FXML
    private DatePicker viewEventDatePicker;

    @FXML
    private Button viewEventDetail;


    @FXML
    void deleteEventFunction() {
        LocalDate date = viewEventDatePicker.getValue();
        String formattedDate = date.toString();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "DELETE FROM userevents WHERE EventDate = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, formattedDate);
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Olay Silme");
            alert.setHeaderText("Olay Siliniyor...");
            alert.setContentText("Olay Başarıyla Silindi.");

            eventTypeLabel.setText("");
            eventDescriptionLabel.setText("");
            dateLabel.setText("");
            startDateLabel.setText("");
            finishDateLabel.setText("");

            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("calenderScreen-view.fxml"));
                Scene newScene = new Scene(root);
                Stage primaryStage = (Stage) deleteButton.getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();

    }

    @FXML
    void updateEventFunction() {
        LocalDate dateEvent = viewEventDatePicker.getValue();
        String formattedDate = dateEvent.toString();

        eventTypeLabel.setEditable(true);
        eventDescriptionLabel.setEditable(true);
        startDateLabel.setEditable(true);
        finishDateLabel.setEditable(true);
        dateLabel.setEditable(true);

        String eventType = eventTypeLabel.getText();
        String eventDescription = eventDescriptionLabel.getText();
        String date = dateLabel.getText();
        String start = startDateLabel.getText();
        String finish = finishDateLabel.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {
            String query = "UPDATE userevents SET EventType = ?, EventDescription = ?, EventDate = ?, StartTime = ?, FinishTime = ? WHERE EventDate = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, eventType);
            statement.setString(2, eventDescription);
            statement.setString(3, date);
            statement.setString(4, start);
            statement.setString(5, finish);
            statement.setString(6, formattedDate);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Olay Güncelleme");
                alert.setHeaderText(null);
                alert.setContentText("Olay Başarıyla Güncellendi.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        eventTypeLabel.setEditable(false);
        eventDescriptionLabel.setEditable(false);
        startDateLabel.setEditable(false);
        finishDateLabel.setEditable(false);
        dateLabel.setEditable(false);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("calenderScreen-view.fxml"));
                Scene newScene = new Scene(root);
                Stage primaryStage = (Stage) updateButton.getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();

    }

    @FXML
    void viewEventDetail() {
        LocalDate date = viewEventDatePicker.getValue();
        String formattedDate = date.toString();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "SELECT EventType, EventDescription, EventDate, StartTime, FinishTime FROM userevents WHERE EventDate = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, formattedDate);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("EventType");
                String description = resultSet.getString("EventDescription");
                String eventDate = resultSet.getString("EventDate");
                String start = resultSet.getString("StartTime");
                String finish = resultSet.getString("FinishTime");

                eventTypeLabel.setText(type);
                eventDescriptionLabel.setText(description);
                dateLabel.setText(eventDate);
                startDateLabel.setText(start);
                finishDateLabel.setText(finish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
