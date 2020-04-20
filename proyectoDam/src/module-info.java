module proyectoDam {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires java.sql;

    exports proyecto;
    exports proyecto.views;
    exports proyecto.Logica;

    opens proyecto.views to javafx.fxml;
}