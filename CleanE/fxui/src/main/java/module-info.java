module CleanE.fxui {
    requires CleanE.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires com.fasterxml.jackson.databind;

    opens ui to javafx.graphics, javafx.fxml; 
}
