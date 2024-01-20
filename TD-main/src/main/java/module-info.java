module edu.gatech.cs2340.td {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.gatech.cs2340.td to javafx.fxml;
    exports edu.gatech.cs2340.td;
}