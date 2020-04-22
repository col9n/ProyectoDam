package proyecto.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proyecto.Logica.Database;

import java.io.IOException;



public class LoginController {
    private Stage stage=new Stage();
    @FXML
    private PasswordField pass;

    @FXML
    private TextField text;

    @FXML
    private Button button;

    @FXML
    private void entrar(ActionEvent event) {
        Database database=new Database();
        boolean existe=database.userExists(text.getText(),pass.getText());
        if(existe )
        {
            changeScene();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error de acceso");
            alert.setHeaderText(null);
            alert.setContentText("Revise usuario y contraseña");
            alert.showAndWait();
        }
    }

    private void changeScene() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyecto/views/Principal.fxml"));
            Parent root = fxmlLoader.load();
            PrincipalController principalController = (PrincipalController) fxmlLoader.getController();
            stage.setTitle("");
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();


    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



}
