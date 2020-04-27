package proyecto.controllers.proveedores;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;

import java.net.URL;
import java.util.*;

public class ModProveedorController implements Initializable {


    private Stage stage = new Stage();
    private ObservableList<Proveedor> proveedorObservableList = Logica.getInstance().getDatabase().getTodosProveedores();
    private  List<Proveedor> listaActualizar = Collections.synchronizedList(new ArrayList());


    @FXML
    private TableView<Proveedor> tableViewProveedor;


    @FXML
    private TableColumn<Proveedor, String> nombreColum;

    @FXML
    private TableColumn<Proveedor, String> dirreccionColum;

    @FXML
    private ComboBox<String> combobox;


    @FXML
    private TextField textProveedor;

    @FXML
    private Button guardarCambios;

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

        nombreColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColum.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Proveedor, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Proveedor, String> t) {
                        Proveedor prov = t.getTableView().getSelectionModel().getSelectedItem();
                        prov.setNombre_proveedor(t.getNewValue());
                        for (Proveedor pro : listaActualizar) {
                            if (pro.getId_proveedor() == prov.getId_proveedor())
                                listaActualizar.remove(pro);
                        }
                        listaActualizar.add(prov);
                    }
                }
        );

        dirreccionColum.setCellFactory(TextFieldTableCell.forTableColumn());
        dirreccionColum.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Proveedor, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Proveedor, String> t) {
                        Proveedor prov = t.getTableView().getSelectionModel().getSelectedItem();
                        prov.setDireccion_proveedor(t.getNewValue());
                        for (Proveedor pro : listaActualizar) {
                            if (pro.getId_proveedor() == prov.getId_proveedor())
                                listaActualizar.remove(pro);
                        }
                        listaActualizar.add(prov);
                    }
                }
        );


    }



    private void filtrarLista() {
        String opcion = combobox.getSelectionModel().getSelectedItem();
        ObservableList<Proveedor> listaFiltrada = FXCollections.observableArrayList();
        if (opcion.equalsIgnoreCase("Todo")) {
            for (Proveedor proveedor : proveedorObservableList) {
                if (proveedor.toString().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if (opcion.equalsIgnoreCase("ID")) {
            for (Proveedor proveedor : proveedorObservableList) {
                if (String.valueOf(proveedor.getId_proveedor()).contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if (opcion.equalsIgnoreCase("Nombre")) {
            for (Proveedor proveedor : proveedorObservableList) {
                if (proveedor.getNombre_proveedor().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if (opcion.equalsIgnoreCase("Direccion")) {
            for (Proveedor proveedor : proveedorObservableList) {
                if (proveedor.getDireccion_proveedor().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        tableViewProveedor.setItems(listaFiltrada);
    }

    @FXML
    void guardarCambio(ActionEvent event) {
        if(listaActualizar.size()>0){
        String cambios="Estos son los cambio a guardar:\n\n";
        for (Proveedor pro:listaActualizar) {
            cambios = cambios+("-"+pro.getId_proveedor()+" nombre: "+pro.getNombre_proveedor()+" dirrecion: "+pro.getDireccion_proveedor()+"\n");
        }
        cambios=cambios+"\nFilas afectadas :"+listaActualizar.size();
        Alert alerta=Logica.getInstance().alertaGet("Realizar cambios",cambios, Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.get() == ButtonType.OK)
            System.out.println("update");
        if(result.get() == ButtonType.CANCEL)
            System.out.println("cancel");
        listaActualizar.clear();
        }

       else
           Logica.getInstance().alertaShow("Realizar cambios","Ningun cambio realizado", Alert.AlertType.INFORMATION);

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}


