module com.example.monstergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.monstergame to javafx.fxml;
    exports com.example.monstergame;
}