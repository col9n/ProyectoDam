package proyecto.Logica;
import javafx.scene.control.Alert;
import proyecto.modelos.Usuario;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Database
 * @author Eduardo
 *
 */
public class Database {

  private static final Logger logger = Logger.getLogger(Database.class.getName());
  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_CONNECTION = "jdbc:mysql://ec2-100-26-98-199.compute-1.amazonaws.com:8081/proyecto";
  //private static final String DB_CONNECTION = "jdbc:mysql://localhost/proyectodam";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "mypass123";

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

}