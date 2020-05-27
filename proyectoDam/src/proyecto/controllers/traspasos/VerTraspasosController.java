package proyecto.controllers.traspasos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import proyecto.Logica.Logica;
import proyecto.modelos.proveedores.Proveedor;
import proyecto.modelos.traspasos.Ordenes;
import proyecto.util.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class VerTraspasosController implements Initializable {
    private ObservableList<Ordenes> proveedorObservableList=Logica.getInstance().getDatabase().getTodasOrdenes(Logica.getInstance().getUsuario().getId_centro());

    @FXML
    private TableView<Ordenes> tableViewProveedor;

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
        ObservableList<Ordenes> listaFiltrada= FXCollections.observableArrayList();
        if(opcion.equalsIgnoreCase("Todo"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(orden.toString()).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        if(opcion.equalsIgnoreCase("ID Traspaso"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(String.valueOf(orden.getId_orden())).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        if(opcion.equalsIgnoreCase("ID Centro origen"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(String.valueOf(orden.getCentro_salida())).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        if(opcion.equalsIgnoreCase("ID Centro destino"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(String.valueOf(orden.getCentro_destino())).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        if(opcion.equalsIgnoreCase("Estado"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(String.valueOf(orden.getEstado())).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        if(opcion.equalsIgnoreCase("Fecha"))
        {
            for (Ordenes orden:proveedorObservableList) {
                if(Util.stringToMayus(String.valueOf(orden.getFecha())).contains(Util.stringToMayus(textProveedor.getText())))
                    listaFiltrada.add(orden);
            }
        }
        tableViewProveedor.setItems(listaFiltrada);


    }

}
