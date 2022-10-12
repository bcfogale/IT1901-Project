module gr2244.fxui {
    requires gr2244.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;

    opens ui to javafx.graphics, javafx.fxml; 
}
