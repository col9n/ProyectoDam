package proyecto.Logica;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.util.Callback;
import proyecto.modelos.*;
import proyecto.util.Util;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Database
 * @author Eduardo
 *
 */
public class Database {



  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  //private static final String DB_CONNECTION = "jdbc:mysql://ec2-52-0-66-200.compute-1.amazonaws.com:3306?autoReconnect=true&useSSL=false";
  private static final String DB_CONNECTION = "jdbc:mysql://localhost/proyectodam?autoReconnect=true&useSSL=false";
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
     Util.alertaShow("Error de conexion a la base de datos","No se puede conectar con la base de datos",Alert.AlertType.ERROR);
    }

    return connection;
  }

  public boolean userExists(String user, String pass) {
    Connection connection = null;
    PreparedStatement psSQL = null;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      psSQL = connection.prepareStatement("SELECT `id_usuario`,`nombre_usuario`,`apellido1`,`apellido2`,`usuario`,`id_centro`,`borradoLogico` FROM `usuarios` where `usuario` = ? and pass=MD5(?);");
      psSQL.setString(1, user);
      psSQL.setString(2, pass);
      ResultSet rs = psSQL.executeQuery();
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == true)
          return false;
        int id_usuario = rs.getInt("id_usuario");
        String nombre_usuario = rs.getString("nombre_usuario");
        String apellido1 = rs.getString("apellido1");
        String apellido2 = rs.getString("apellido2");
        String usuario = rs.getString("usuario");
        int id_centro = rs.getInt("id_centro");

        Usuario usuario1 = new Usuario(id_usuario, nombre_usuario, apellido1, apellido2, usuario, id_centro);
        Logica.getInstance().setUsuario(usuario1);
        return true;
      }
      return false;
    } catch (SQLException exception) {
    } finally {
      if (null != psSQL) {
        try {
          psSQL.close();
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

  public boolean proveedorExists(String proveedor) {
    Connection connection = null;
    PreparedStatement psSQL = null;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      psSQL = connection.prepareStatement("SELECT `borradoLogico` FROM `proveedores` where upper(nombre_proveedor)=upper(?)");
      psSQL.setString(1, proveedor);

      ResultSet rs = psSQL.executeQuery();
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == true)
          return false;
        return true;
      }
      return false;
    } catch (SQLException exception) {
    } finally {
      if (null != psSQL) {
        try {
          psSQL.close();
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
    ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql = "SELECT `id_proveedor`,`nombre_proveedor`,`direccion_proveedor`,`borradoLogico` FROM `proveedores`";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == false) {
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

  public ObservableList<ProveedorEliminar> getTodosProveedoresEliminar() {
    Connection connection = null;
    PreparedStatement statement = null;
    ObservableList<ProveedorEliminar> listaProveedores = FXCollections.observableArrayList(
            new Callback<ProveedorEliminar, Observable[]>() {
              @Override
              public Observable[] call(ProveedorEliminar param) {
                return new Observable[]{
                        param.borradoLogicoProperty()
                };
              }
            }
    );
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql = "SELECT `id_proveedor`,`nombre_proveedor`,`direccion_proveedor`,`borradoLogico` FROM `proveedores`";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == false) {
          int id_proveedor = rs.getInt("id_proveedor");
          String nombre_proveedor = rs.getString("nombre_proveedor");
          String direccion_proveedor = rs.getString("direccion_proveedor");
          BooleanProperty borradoLogico = new SimpleBooleanProperty(true);
          listaProveedores.add(new ProveedorEliminar(id_proveedor, nombre_proveedor, direccion_proveedor, borradoLogico));
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



  public int addProveedor(String nombre, String direccion) {
    Connection connection = null;
    PreparedStatement psInsertar = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      psInsertar = connection.prepareStatement("INSERT INTO `proveedores` (`nombre_proveedor`, `direccion_proveedor`, `borradoLogico`) VALUES (?, ?, '0')");
      psInsertar.setString(1, nombre);
      psInsertar.setString(2, direccion);
      rs = psInsertar.executeUpdate();
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != psInsertar) {
        try {
          psInsertar.close();
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

  public int updateProveedor(List<Proveedor> listaActualizar) {
    Connection connection = null;
    PreparedStatement update = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      for (Proveedor prov : listaActualizar) {
        String consulta = "UPDATE `proveedores` SET `nombre_proveedor` = ? ,nombre_proveedor= ? WHERE upper(`id_proveedor`) = upper(?)";
        update = connection.prepareStatement(consulta);
        update.setString(1, prov.getNombre_proveedor());
        update.setString(2, prov.getDireccion_proveedor());
        update.setInt(3, prov.getId_proveedor());

        rs = rs + update.executeUpdate();

      }
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != update) {
        try {
          update.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.rollback();
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return rs;
  }


  public int deleteProveedores(List<ProveedorEliminar> listaBorrados) {
    Connection connection = null;
    PreparedStatement update = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      for (Proveedor prov : listaBorrados) {
        String consulta = "DELETE FROM `proveedores` WHERE upper(`id_proveedor`) = upper(?)";
        update = connection.prepareStatement(consulta);
        update.setInt(1, prov.getId_proveedor());

        rs = rs + update.executeUpdate();

      }
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != update) {
        try {
          update.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.rollback();
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return rs;
  }

  public boolean productoExists(String producto) {
    Connection connection = null;
    PreparedStatement psSQL = null;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      psSQL = connection.prepareStatement("SELECT `borradoLogico` FROM `productos` where upper(nombre_producto)=upper(?)");
      psSQL.setString(1, producto);

      ResultSet rs = psSQL.executeQuery();
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == true)
          return false;
        return true;
      }
      return false;
    } catch (SQLException exception) {
    } finally {
      if (null != psSQL) {
        try {
          psSQL.close();
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

  public ObservableList<Producto> getTodosProductos() {
    Connection connection = null;
    PreparedStatement statement = null;
    ObservableList<Producto> listaProveedores = FXCollections.observableArrayList();
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql = "SELECT `id_producto`,`nombre_producto`,`id_proveedor`,`borradoLogico` FROM `productos`";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == false) {
          int id_producto = rs.getInt("id_producto");
          String nombre_producto = rs.getString("nombre_producto");
          int id_proveedor = rs.getInt("id_proveedor");
          listaProveedores.add(new Producto(id_producto, nombre_producto, id_proveedor));
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

  public ObservableList<ProductoEliminar> getTodosProductosEliminar() {
    Connection connection = null;
    PreparedStatement statement = null;
    ObservableList<ProductoEliminar> listaProveedores = FXCollections.observableArrayList(
            new Callback<ProductoEliminar, Observable[]>() {
              @Override
              public Observable[] call(ProductoEliminar param) {
                return new Observable[]{
                        param.borradoLogicoProperty()
                };
              }
            }
    );
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      Statement stmt = connection.createStatement();
      String sql = "SELECT `id_producto`,`nombre_producto`,`id_proveedor`,`borradoLogico` FROM `productos`";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (rs.getBoolean("borradoLogico") == false) {
          int id_producto = rs.getInt("id_producto");
          String nombre_producto = rs.getString("nombre_producto");
          int id_proveedor = rs.getInt("id_proveedor");
          BooleanProperty borradoLogico = new SimpleBooleanProperty(true);
          listaProveedores.add(new ProductoEliminar(id_producto, nombre_producto, id_proveedor, borradoLogico));
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



  public int addProducto(String nombre, int id_proveedor) {
    Connection connection = null;
    PreparedStatement psInsertar = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      psInsertar = connection.prepareStatement("INSERT INTO `productos` (`nombre_producto`, `id_proveedor`,`borradoLogico`) VALUES (?, ?, '0')");
      psInsertar.setString(1, nombre);
      psInsertar.setInt(2, id_proveedor);
      rs = psInsertar.executeUpdate();
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != psInsertar) {
        try {
          psInsertar.close();
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

  public int updateProducto(List<Producto> listaActualizar) {
    Connection connection = null;
    PreparedStatement update = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      for (Producto prov : listaActualizar) {
        String consulta = "UPDATE `productos` SET `nombre_producto` = upper(?)  WHERE `id_producto` = ?";
        update = connection.prepareStatement(consulta);
        update.setString(1, prov.getNombre_producto());
        update.setInt(2, prov.getId_producto());

        rs = rs + update.executeUpdate();

      }
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != update) {
        try {
          update.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.rollback();
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return rs;
  }


  public int deleteProductos(List<ProductoEliminar> listaBorrados) {
    Connection connection = null;
    PreparedStatement update = null;
    int rs = 0;
    try {
      connection = Database.getDBConnection();
      connection.setAutoCommit(false);
      for (Producto prov : listaBorrados) {
        String consulta = "DELETE FROM `productos` WHERE `id_producto` = ?";
        update = connection.prepareStatement(consulta);
        update.setInt(1, prov.getId_producto());

        rs = rs + update.executeUpdate();

      }
      connection.commit();
      return rs;
    } catch (SQLException exception) {
    } finally {
      if (null != update) {
        try {
          update.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (null != connection) {
        try {
          connection.rollback();
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return rs;
  }
}