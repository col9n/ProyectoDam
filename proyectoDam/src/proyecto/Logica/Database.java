package proyecto.Logica;
import javafx.scene.control.Alert;

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
  private static final String DB_CONNECTION = "jdbc:mysql://localhost/proyectodam";
  private static final String DB_USER = "proyecto";
  private static final String DB_PASSWORD = "proyecto";

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
      String sql="select user,pass from usuarios where  user = '"+user+"' and pass=MD5('"+pass+"')";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
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