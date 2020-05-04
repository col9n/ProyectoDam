package proyecto.controllers.productos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import proyecto.Logica.Logica;
import proyecto.modelos.Producto;
import proyecto.modelos.Proveedor;

import java.net.URL;
import java.util.ResourceBundle;

public class VerProductoController implements Initializable {
    private ObservableList<Producto> proveedorObservableList=Logica.getInstance().getDatabase().getTodosProductos();

    @FXML
    private TableView<Producto> tableViewProveedor;

    @FXML
    private ComboBox<String> combobox;


    @FXML
    private TextField textProveedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewProveedor.setItems(proveedorObservableList);

        textProveedor.textProperty().addListener((observable, oldValue, newValue) -> filtrarLista());
    }


    private void filtrarLista() {
        String opcion=combobox.getSelectionModel().getSelectedItem();
        ObservableList<Producto> listaFiltrada= FXCollections.observableArrayList();
        if(opcion.equalsIgnoreCase("Todo"))
        {
            for (Producto producto:proveedorObservableList) {
                if(producto.toString().toUpperCase().contains(textProveedor.getText().toUpperCase()))
                    listaFiltrada.add(producto);
            }
        }
        if(opcion.equalsIgnoreCase("ID producto"))
        {
            for (Producto producto:proveedorObservableList) {
                if(String.valueOf(producto.getId_producto()).contains(textProveedor.getText()))
                    listaFiltrada.add(producto);
            }
        }
        if(opcion.equalsIgnoreCase("Nombre"))
        {
            for (Producto producto:proveedorObservableList) {
                if(producto.getNombre_producto().toUpperCase().contains(textProveedor.getText().toUpperCase()))
                    listaFiltrada.add(producto);
            }
        }

        if(opcion.equalsIgnoreCase("ID proveedor"))
        {
            for (Producto producto:proveedorObservableList) {
                if(String.valueOf(producto.getId_proveedor()).contains(textProveedor.getText()))
                    listaFiltrada.add(producto);
            }
        }
        tableViewProveedor.setItems(listaFiltrada);
    }

}
