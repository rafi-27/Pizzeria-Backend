module ies.thiar {
    requires javafx.controls;
    requires javafx.fxml;

    opens ies.thiar to javafx.fxml;
    exports ies.thiar;

    exports ies.thiar.Control;

}
