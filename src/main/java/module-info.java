module com.example.calenderproject_202802039 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.calenderproject_202802039 to javafx.fxml;
    exports com.example.calenderproject_202802039;
}