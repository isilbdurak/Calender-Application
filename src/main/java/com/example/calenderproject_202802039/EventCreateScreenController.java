package com.example.calenderproject_202802039;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Date;

public class EventCreateScreenController {
    ObservableList<String> EventStartTimes = FXCollections.observableArrayList("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
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
    ChoiceBox EventTypes = new ChoiceBox(FXCollections.observableArrayList("Toplantı", "Ders", "Doğum Günü", "Yemek Planı", "İş"));

    @FXML
    void eventCompletedFunction(ActionEvent event) {

    }

}
