module proyectoDam {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires java.sql;

    exports proyecto;
    exports proyecto.modelos;
    exports proyecto.controllers;
    exports proyecto.Logica;

    opens proyecto.controllers to javafx.fxml;
    opens proyecto.controllers.proveedores to javafx.fxml;
}