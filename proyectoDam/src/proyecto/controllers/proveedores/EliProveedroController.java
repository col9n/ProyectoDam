package proyecto.controllers.proveedores;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import proyecto.Logica.Logica;
import proyecto.modelos.Proveedor;
import proyecto.modelos.ProveedorEliminar;

import java.net.URL;
import java.util.*;

public class EliProveedroController implements Initializable {
    private Stage stage = new Stage();
    private ObservableList<ProveedorEliminar> proveedorObservableList=Logica.getInstance().getDatabase().getTodosProveedoresEliminar();
    List <ProveedorEliminar> listaBorrar = new <Proveedor> ArrayList();

    @FXML
    private TableView<ProveedorEliminar> tableViewProveedor;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField textProveedor;

    @FXML
    private Button eliminar;



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

        proveedorObservableList.addListener(new ListChangeListener<Proveedor>() {
            @Override
            public void onChanged(Change<? extends Proveedor> c) {
                while (c.next()) {
                    if (c.wasUpdated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            ProveedorEliminar prov =proveedorObservableList.get(i);
                            listaBorrar.remove(prov);
                            if(!prov.isBorradoLogico()) {
                                listaBorrar.add(prov);
                            }
                    }
                }
            }
        }
        });

    }


    private void filtrarLista() {
        String opcion=combobox.getSelectionModel().getSelectedItem();
        ObservableList<ProveedorEliminar> listaFiltrada= FXCollections.observableArrayList();
        if(opcion.equalsIgnoreCase("Todo"))
        {
            for (ProveedorEliminar proveedor:proveedorObservableList) {
                if(proveedor.toString().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("ID"))
        {
            for (ProveedorEliminar proveedor:proveedorObservableList) {
                if(String.valueOf(proveedor.getId_proveedor()).contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("Nombre"))
        {
            for (ProveedorEliminar proveedor:proveedorObservableList) {
                if(proveedor.getNombre_proveedor().contains(textProveedor.getText()))
                    listaFiltrada.add(proveedor);
            }
        }
        if(opcion.equalsIgnoreCase("Direccion"))
        {
            for (ProveedorEliminar proveedor:proveedorObservableList) {
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

    @FXML
    void eliminar(ActionEvent event) {
        if(listaBorrar.size()>0){
            String borrados="Estos son los proveedores a borrar:\n\n";
            for (Proveedor pro: listaBorrar) {
                borrados = borrados+("-"+pro.getId_proveedor()+" nombre: "+pro.getNombre_proveedor()+" dirrecion: "+pro.getDireccion_proveedor()+"\n");
            }
            borrados=borrados+"\nFilas afectadas :"+ listaBorrar.size();
            Alert alerta=Logica.getInstance().alertaGet("Realizar borrado",borrados, Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alerta.showAndWait();
            if(result.get() == ButtonType.OK) {
                int afectadas=Logica.getInstance().getDatabase().deleteProveedores(listaBorrar);
                if(afectadas!= listaBorrar.size())
                {
                    Logica.getInstance().alertaShow("Realizar borrado","Se produjo un error en el borrado", Alert.AlertType.ERROR);
                }
                else
                    Logica.getInstance().alertaShow("Realizar borrado","Numero de borrados realizados: "+ listaBorrar.size()+" ", Alert.AlertType.INFORMATION);
                actualizarTableView();
            }

        }
        else
            Logica.getInstance().alertaShow("Realizar borrado","Ningun borrado realizado", Alert.AlertType.INFORMATION);

    }
    private void actualizarTableView(){
        for(ProveedorEliminar prov:listaBorrar)
        {
            proveedorObservableList.remove(prov);
        }
        listaBorrar.clear();
    }

}
