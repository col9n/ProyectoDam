package proyecto.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import proyecto.Logica.Logica;
import proyecto.util.Util;

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

        boolean existe= Logica.getInstance().getDatabase().userExists(text.getText(),pass.getText());
        if(existe )
        {
            changeScene();
        }
        else
        {
            Util.alertaShow("Error de acceso","Revise usuario  y contraseña", Alert.AlertType.INFORMATION);
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
