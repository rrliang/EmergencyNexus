module com.flyinggeese.emergencynexus {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.flyinggeese.emergencynexus to javafx.fxml;
    exports com.flyinggeese.emergencynexus;
}