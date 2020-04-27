package proyecto.controllers.proveedores;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EliProveedroController implements Initializable {
    private Stage stage = new Stage();
    private ObservableList<Proveedor> proveedorObservableList=Logica.getInstance().getDatabase().getTodosProveedores();
    private List<Proveedor> listaActualizar = Collections.synchronizedList(new ArrayList());

    @FXML
    private TableView<Proveedor> tableViewProveedor;

    @FXML
    private TableColumn<?, ?> borrarColumna;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField textProveedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewProveedor.setItems(proveedorObservableList);

        textProveedor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                filtrarLista();
            }
        });


        borrarColumna.setCellValueFactory(
                c -> {
                    Proveedor candidate = (Proveedor) c.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.selectedProperty().setValue(candidate.isBorradoLogico());
                    checkBox
                            .selectedProperty()
                            .addListener((ov, old_val, new_val) -> candidate.setBorradoLogico(new_val));
                    return new SimpleObjectProperty(checkBox);
                });
    }


    private void filtrarLista() {
        String opcion=combobox.getSelectionModel().getSelectedItem();
        ObservableList<Proveedor> listaFiltrada= FXCollections.observableArrayList();
        if(opcion.equalsIgnoreCase("Todo"))
        {
            for (Proveedor proveedor:proveedorObservableList) {
                if(proveedor.toString().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("ID"))
        {
            for (Proveedor proveedor:proveedorObservableList) {
                if(String.valueOf(proveedor.getId_proveedor()).contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("Nombre"))
        {
            for (Proveedor proveedor:proveedorObservableList) {
                if(proveedor.getNombre_proveedor().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("Direccion"))
        {
            for (Proveedor proveedor:proveedorObservableList) {
                if(proveedor.getDireccion_proveedor().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        tableViewProveedor.setItems(listaFiltrada);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
