package proyecto.modelos.proveedores;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class Proveedor {
    private int id_proveedor;
    private String nombre_proveedor;
    private String direccion_proveedor;

    public Proveedor(int id_proveedor, String nombre_proveedor, String direccion_proveedor) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.direccion_proveedor = direccion_proveedor;
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




    @Override
    public String toString() {
        return "ID "+this.id_proveedor+" nombre "+this.nombre_proveedor;
    }

    public String verInfor(){
        return "Proveedor{" +
                "id_proveedor=" + id_proveedor +
                ", nombre_proveedor='" + nombre_proveedor + '\'' +
                ", direccion_proveedor='" + direccion_proveedor + '\'' +
                '}';
    }
}
