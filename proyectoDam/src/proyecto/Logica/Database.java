package proyecto.Logica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import proyecto.modelos.Proveedor;
import proyecto.modelos.Usuario;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Database
 * @author Eduardo
 *
 */
public class Database {

  private static final Logger logger = Logger.getLogger(Database.class.getName());
  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  //private static final String DB_CONNECTION = "jdbc:mysql://ec2-52-90-114-135.compute-1.amazonaws.com:3306/proyectodam";
  private static final String DB_CONNECTION = "jdbc:mysql://localhost/proyectodam";
  private static final String DB_USER = "root";
  //private static final String DB_PASSWORD = "mypass123";
  private static final String DB_PASSWORD = "root";

  public Database() {

  }

  private static Connection getDBConnection() {
    Connection connection = null;
    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException exception) {
    }

    try {
      connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
      return connection;
    } catch (SQLException exception) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error de conexion a la base de datos");
      alert.setHeaderText(null);
      alert.setContentText("No se puede conectar con la base de datos");
      alert.showAndWait();
    }

    return connection;
  }

  public boolean userExists(String user,String pass) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql="SELECT `id_usuario`,`nombre_usuario`,`apellido1`,`apellido2`,`usuario`,`id_centro`,`borradoLogico` FROM `usuarios` where `usuario` = '"+user+"' and pass=MD5('"+pass+"')";


      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if(rs.getBoolean("borradoLogico")==true)
          return false;
        int id_usuario=rs.getInt("id_usuario");
        String nombre_usuario=rs.getString("nombre_usuario");
        String apellido1=rs.getString("apellido1");
        String apellido2=rs.getString("apellido2");
        String usuario=rs.getString("usuario");
        int id_centro=rs.getInt("id_centro");

        Usuario usuario1=new Usuario(id_usuario,nombre_usuario,apellido1,apellido2,usuario,id_centro);
        Logica.getInstance().setUsuario(usuario1);



        return true;
      }
      return false;
    } catch (SQLException exception) {
    } finally {
      if (null != statement) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public ObservableList<Proveedor> getTodosProveedores() {
    Connection connection = null;
    PreparedStatement statement = null;
    ObservableList<Proveedor> listaProveedores= FXCollections.observableArrayList();
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql="SELECT `id_proveedor`,`nombre_proveedor`,`direccion_proveedor`,`borradoLogico` FROM `proveedores`";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if(rs.getBoolean("borradoLogico")==false) {
          int id_proveedor = rs.getInt("id_proveedor");
          String nombre_proveedor = rs.getString("nombre_proveedor");
          String direccion_proveedor = rs.getString("direccion_proveedor");


          listaProveedores.add(new Proveedor(id_proveedor, nombre_proveedor, direccion_proveedor));
        }
      }
      return listaProveedores;
    } catch (SQLException exception) {
    } finally {
      if (null != statement) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return listaProveedores;
  }

  public int addProveedor(String nombre,String direccion) {
    Connection connection = null;
    PreparedStatement statement = null;
    ArrayList<Proveedor> listaProveedores= new ArrayList<Proveedor>();
    int rs=0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(true);
      Statement stmt = connection.createStatement();
      String sql="INSERT INTO `proveedores` (`nombre_proveedor`, `direccion_proveedor`, `borradoLogico`) VALUES ('"+nombre+"', '"+direccion+"', '0')";
      System.out.println(sql);
      rs = stmt.executeUpdate(sql);
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != statement) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return rs;
  }

}