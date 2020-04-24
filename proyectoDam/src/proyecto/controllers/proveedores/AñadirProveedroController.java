package proyecto.controllers.proveedores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;

import java.net.URL;
import java.util.ResourceBundle;

public class AñadirProveedroController implements Initializable {
    private Stage stage = new Stage();

    @FXML
    private Button guardarProveedor;

    @FXML
    private Button limpiarProveedor;


    @FXML
    private TextField nombreProveedor;

    @FXML
    private TextField direccionProveedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void limpiarAddProveedor(ActionEvent event) {
        nombreProveedor.setText("");
        direccionProveedor.setText("");

    }

    @FXML
    private void guardarProveedor(ActionEvent event) {
        String nombre =nombreProveedor.getText();
        String direecion=  direccionProveedor.getText();
        if(!nombre.isEmpty() && !direecion.isEmpty()) {
            int inserto = Logica.getInstance().getDatabase().addProveedor(nombre, direecion);
            if (inserto != 0)
                Logica.getInstance().alerta("Consulta realizada", "El proveedor fue guardado", Alert.AlertType.CONFIRMATION);
            else
                Logica.getInstance().alerta("Fallo de consulta","El proveedor no se pudo guardar", Alert.AlertType.ERROR);
            limpiarProveedor.fire();
        }
        else
            Logica.getInstance().alerta("Campos vacios","Nombre o direccion del proveedor vacio", Alert.AlertType.WARNING);

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
