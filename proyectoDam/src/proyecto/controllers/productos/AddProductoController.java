package proyecto.controllers.productos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;
import proyecto.util.Util;

import java.net.URL;
import java.util.ResourceBundle;

import static proyecto.util.Util.activacionBoton;
import static proyecto.util.Util.activacionBotonComboBox;

public class AddProductoController implements Initializable {

    @FXML
    private TextField nombreProducto;

    @FXML
    private Button guardarProveedor;

    @FXML
    private Button limpiarProveedor;

    @FXML
    private ComboBox<Proveedor> nombreProveedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nombreProveedor.setItems(Logica.getInstance().getDatabase().getTodosProveedores());
        activacionBotonComboBox(nombreProducto,nombreProveedor,guardarProveedor);
    }

    @FXML
    private void limpiarAddProveedor() {
        nombreProducto.setText("");
    }

    @FXML
    private void guardarProveedor() {
        String nombre =nombreProducto.getText();
        Proveedor proveedor=  nombreProveedor.getSelectionModel().getSelectedItem();
            boolean existe= Logica.getInstance().getDatabase().productoExists(nombre);
            if(!existe)
            {
                int inserto = Logica.getInstance().getDatabase().addProducto(nombre, proveedor.getId_proveedor());
                if (inserto != 0)
                    Util.alertaShow("Consulta realizada", "El producto fue guardado con nombre: "+nombre+ " y proveedor: "+proveedor.getNombre_proveedor(), Alert.AlertType.INFORMATION);
                else
                    Util.alertaShow("Fallo de consulta","El proveedor no se pudo guardar", Alert.AlertType.ERROR);
            }
            else
                Util.alertaShow("Campos vacios","El proveedor :"+nombre+" ya esta creado", Alert.AlertType.WARNING);
        limpiarProveedor.fire();

    }

}
