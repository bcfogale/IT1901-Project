module CleanE.fxui {
    requires CleanE.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    opens ui to javafx.graphics, javafx.fxml; 
}
