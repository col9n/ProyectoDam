package proyecto.controllers.proveedores;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class VerProveedroController implements Initializable {
    private Stage stage = new Stage();

    @FXML
    private TableView<Proveedor> tableViewProveedor;

    @FXML
    private Button botonTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewProveedor.setItems(Logica.getInstance().getDatabase().getTodosProveedores());




    }

    @FXML
    void setT(ActionEvent event) {
        ObservableList<Proveedor> observableListProveedor =Logica.getInstance().getDatabase().getTodosProveedores();
        tableViewProveedor.setItems(observableListProveedor);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
