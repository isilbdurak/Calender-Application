package com.example.calenderproject_202802039;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private Button createEventButton;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    private User getUser() {
        return user;
    }


    @FXML
    void createEvent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventCreateScreen-view.fxml"));
            Parent eventCreationParent = loader.load();
            EventCreateScreenController eventCreationController = loader.getController();

            User user = UserData.getUser();
            eventCreationController.setUser(user);

            Stage eventCreationStage = new Stage();
            eventCreationStage.setScene(new Scene(eventCreationParent));
            eventCreationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void deleteEventFunction() {
        User user = getUser();
        LocalDate date = viewEventDatePicker.getValue();
        String formattedDate = date.toString();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "DELETE ue FROM userevents ue " +
                    "INNER JOIN Users u ON ue.username = u.UserUsername " +
                    "WHERE ue.EventDate = ? AND u.UserUsername = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, formattedDate);
            statement.setString(2, user.getUsername());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateEventFunction() {
        User user = getUser();
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
            String query = "UPDATE userevents ue " +
                    "INNER JOIN Users u ON ue.username = u.UserUsername " +
                    "SET ue.EventType = ?, ue.EventDescription = ?, ue.EventDate = ?, ue.StartTime = ?, ue.FinishTime = ? " +
                    "WHERE ue.EventDate = ? AND u.UserUsername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, eventType);
            statement.setString(2, eventDescription);
            statement.setString(3, date);
            statement.setString(4, start);
            statement.setString(5, finish);
            statement.setString(6, formattedDate);
            statement.setString(7, user.getUsername());

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

    }

    @FXML
    void viewEventDetail() {
        eventTypeLabel.setEditable(true);
        eventDescriptionLabel.setEditable(true);
        startDateLabel.setEditable(true);
        finishDateLabel.setEditable(true);
        dateLabel.setEditable(true);

        LocalDate date = viewEventDatePicker.getValue();
        String formattedDate = date.toString();

        User user = getUser();
        String username = user.getUsername();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calenderapp", "root", "180302")) {

            String query = "SELECT EventType, EventDescription, EventDate, StartTime, FinishTime " + "FROM userevents " + "JOIN Users ON userevents.username = Users.UserUsername " + "WHERE EventDate = ? AND Users.UserUsername = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, formattedDate);
            statement.setString(2, username);
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
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Olay Bulunamadı.");
                alert.setHeaderText("Bu tarih için bir olay yaratmadınız.");
                alert.setContentText("Diğer tarihleri kontrol edebilirsiniz.");
                alert.showAndWait();

                eventTypeLabel.setText("");
                eventDescriptionLabel.setText("");
                dateLabel.setText("");
                startDateLabel.setText("");
                finishDateLabel.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
