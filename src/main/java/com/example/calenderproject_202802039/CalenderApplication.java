package com.example.calenderproject_202802039;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalenderApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalenderApplication.class.getResource("mainScreen-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 490, 490);
        stage.setTitle("To Do Calender");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}