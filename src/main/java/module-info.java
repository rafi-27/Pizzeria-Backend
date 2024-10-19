module ies.thiar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires com.opencsv;
    requires java.sql;

    opens ies.thiar to javafx.fxml;
    exports ies.thiar;

    exports ies.thiar.Control;
    exports ies.thiar.Modelo;

    opens ies.thiar.Modelo to java.xml.bind;
    opens ies.thiar.Control to java.xml.bind;
}
