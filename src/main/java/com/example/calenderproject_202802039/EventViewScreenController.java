package com.example.calenderproject_202802039;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EventViewScreenController implements Initializable {
    @FXML
    private Label dateLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label eventDescriptionLabel;

    @FXML
    private Label eventTypeLabel;

    @FXML
    private Label finishDateLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Button updateButton;

    @FXML
    private DatePicker viewEventDatePicker;


    @FXML
    private Button viewEventDetail;

    @FXML
    void deleteEventFunction() {

    }

    @FXML
    void updateEventFunction() {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
