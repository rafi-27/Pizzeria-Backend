module ies.thiar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;

    opens ies.thiar to javafx.fxml;
    exports ies.thiar;

    exports ies.thiar.Control;
    exports ies.thiar.Modelo;

    opens ies.thiar.Modelo to java.xml.bind;
}
