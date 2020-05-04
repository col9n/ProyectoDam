package proyecto.controllers.productos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import proyecto.Logica.Logica;
import proyecto.modelos.Producto;
import proyecto.modelos.Proveedor;
import proyecto.util.Util;

import java.net.URL;
import java.util.*;

public class ModProductoController implements Initializable {
    private ObservableList<Producto> proveedorObservableList = Logica.getInstance().getDatabase().getTodosProductos();
    private  List<Producto> listaActualizar = Collections.synchronizedList(new ArrayList());


    @FXML
    private TableView<Producto> tableViewProveedor;


    @FXML
    private TableColumn<Producto, String> nombreColum;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField textProveedor;

    @FXML
    private Button guardarCambios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewProveedor.setItems(proveedorObservableList);

        textProveedor.textProperty().addListener((observable, oldValue, newValue) -> filtrarLista());

        nombreColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColum.setOnEditCommit(
                t -> {
                    Producto prov = t.getTableView().getSelectionModel().getSelectedItem();
                    prov.setNombre_producto(t.getNewValue());
                    for (Producto pro : listaActualizar) {
                        if (pro.getId_producto() == prov.getId_producto())
                            listaActualizar.remove(pro);
                    }
                    listaActualizar.add(prov);
                }
        );



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

    @FXML
    void guardarCambio() {
        if(listaActualizar.size()>0){
        StringBuilder cambios= new StringBuilder("Estos son los cambio a guardar:\n\n");
        for (Producto pro:listaActualizar) {
            cambios.append("-").append(pro.getId_producto()).append(" nombre: ").append(pro.getNombre_producto()).append(" id proveedor: ").append(pro.getId_proveedor()).append("\n");
        }
        cambios.append("\nFilas afectadas :").append(listaActualizar.size());
        Alert alerta= Util.alertaGet("Realizar cambios", cambios.toString(), Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.get() == ButtonType.OK) {
            int afectadas=Logica.getInstance().getDatabase().updateProducto(listaActualizar);
            if(afectadas!=listaActualizar.size())
            {
                Util.alertaShow("Realizar cambios","Se produjo un error en el guardado", Alert.AlertType.ERROR);
            }
            else
                Util.alertaShow("Realizar cambios","Numero de cambios realizados: "+listaActualizar.size()+" ", Alert.AlertType.INFORMATION);
        }
        listaActualizar.clear();
        }
       else
            Util.alertaShow("Realizar cambios","Ningun cambio realizado", Alert.AlertType.INFORMATION);

    }

}


