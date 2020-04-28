package proyecto.controllers.proveedores;

import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;

import java.net.URL;
import java.util.*;

public class EliProveedroController implements Initializable {
    private Stage stage = new Stage();
    private ObservableList<Proveedor> proveedorObservableList=Logica.getInstance().getDatabase().getTodosProveedores();
    private List<Proveedor> listaActualizar = Collections.synchronizedList(new ArrayList());

    @FXML
    private TableView<Proveedor> tableViewProveedor;

    @FXML
    private TableColumn<Proveedor, CheckBox> borrado;

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

        /*
        proveedorObservableList.addListener((ListChangeListener<Proveedor>) change -> {
            while (change.next()) {
                if (change.wasUpdated()) {
                    System.out.println(change.wasUpdated());
                }
            }
        });

         */
      //  proveedorObservableList.addListener((new ObservableList<Proveedor>()





        /*
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
        checkBoxColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Proveedor, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Proveedor, Boolean> proveedorSimpleBooleanPropertyCellEditEvent) {
                Proveedor prov = proveedorSimpleBooleanPropertyCellEditEvent.getTableView().getSelectionModel().getSelectedItem();
                System.out.println( prov.toString());
            }
        });

        <TableColumn fx:id="checkBoxColumn" editable="true" prefWidth="75.0" text="Borrado"  >
                <cellValueFactory><PropertyValueFactory property="borradoLogico" /></cellValueFactory>
                <cellFactory><CheckBoxCellFactory /></cellFactory>
            </TableColumn>
         */

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
