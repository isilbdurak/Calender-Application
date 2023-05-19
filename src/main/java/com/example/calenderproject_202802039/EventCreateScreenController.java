package com.example.calenderproject_202802039;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class EventCreateScreenController implements Initializable {
    @FXML
    private Label completedMessageLabel;
    @FXML
    private Button eventCompletedButton;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventExplanation;

    @FXML
    private ChoiceBox<String> eventFinishTime;

    @FXML
    private ChoiceBox<String> eventStartTime;

    @FXML
    private ChoiceBox<String> eventType;
    @FXML
    private ChoiceBox<String> reminderChoiceBox;
    final String[] reminders = {"Only That Day", "Every Week", "Every Month", "Every Year"};
    final String[] startTimes = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    final String[] finishTimes = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    final String[] eventTypes = {"Toplantı", "Ders", "Doğum Günü", "Yemek Planı", "İş"};


    @FXML
    void eventCompletedFunction() {
        saveDatabase();
    }

    public void saveDatabase() {
        String reminder = reminderChoiceBox.getValue();
        String start = eventStartTime.getValue();
        String finish = eventFinishTime.getValue();
        String expText = eventExplanation.getText();
        String eventT = eventType.getValue();
        LocalDate date = eventDate.getValue();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "INSERT INTO userevents (EventType, EventDescription, EventDate, StartTime, FinishTime, EventReminder)  VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, eventT);
            statement.setString(2, expText);
            statement.setString(3, String.valueOf(date));
            statement.setString(4, start);
            statement.setString(5, finish);
            statement.setString(6, reminder);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        completedMessageLabel.setText("OLAY BAŞARIYLA TANIMLANDI.");
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("calenderScreen-view.fxml"));
                Scene newScene = new Scene(root);
                Stage primaryStage = (Stage) eventCompletedButton.getScene().getWindow();
                primaryStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reminderChoiceBox.getItems().addAll(reminders);
        eventStartTime.getItems().addAll(startTimes);
        eventFinishTime.getItems().addAll(finishTimes);
        eventType.getItems().addAll(eventTypes);
    }
}
