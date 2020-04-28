package proyecto.modelos;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;

public class Proveedor {
    private int id_proveedor;
    private String nombre_proveedor;
    private String direccion_proveedor;
    private boolean borradoLogico;
    private CheckBox box=new CheckBox();

    public Proveedor(int id_proveedor, String nombre_proveedor, String direccion_proveedor) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.direccion_proveedor = direccion_proveedor;



    }

    public Proveedor(int id_proveedor, String nombre_proveedor, String direccion_proveedor, boolean borradoLogico) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.direccion_proveedor = direccion_proveedor;
        this.borradoLogico = borradoLogico;
        box.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue)
                    System.out.println("true");
                else
                    System.out.println("false");
            }
        });
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public boolean getBorradoLogico() {
        return this.borradoLogico;
    }

    public void setBorradoLogico(boolean borrado) {
        this.borradoLogico=borrado;
    }

    public CheckBox getBox() {
        return box;
    }

    public void setBox(CheckBox box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id_proveedor=" + id_proveedor +
                ", nombre_proveedor='" + nombre_proveedor + '\'' +
                ", direccion_proveedor='" + direccion_proveedor + '\'' +
                ", borradoLogico=" + borradoLogico +
                '}';
    }
}
