package proyecto.modelos;

public class Proveedor {
    private int id_proveedor;
    private String nombre_proveedor;
    private String direccion_proveedor;
    private boolean borradoLogico;

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


    public boolean isBorradoLogico() {
        return borradoLogico;
    }

    public void setBorradoLogico(boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
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
