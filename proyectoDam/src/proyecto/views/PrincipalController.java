package proyecto.views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import proyecto.Logica.Logica;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PrincipalController implements Initializable {
    private Stage stage = new Stage();
    private Timeline timeline = Logica.getInstance().getTimeline();

    /* Zona de border pane medio pagina */
    @FXML
    private BorderPane borderPane=new BorderPane();

    /*------------- Zona del tab proveedor --------------*/
    /* Zona de ver provedor*/
    @FXML
    private Button provedButVer;

    @FXML
    private Button prodButAña;

    /* Zona de añadir provedor*/
    @FXML
    private TextField nombreProveedor;

    @FXML
    private TextField direccionProveedor;

    @FXML
    private Button guardarProveedor;

    @FXML
    private Button limpiarProveedor;

    /* Zona de mod provedor*/
    @FXML
    private Button provedButMod;

    /* Zona de eliminar provedor*/
    @FXML
    private Button provedButEli;



    /* Zona de border pane pie de pagina */

    /* Zona de border pane pie de pagina izquierda */
    @FXML
    private Label labelBotIzq;

    /* Zona de border pane pie de pagina izquierda */
    @FXML
    private Label labelBotDer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelBotIzq.setText(Logica.getInstance().getUsuario().toString());
        setFechaYHora();


    }

    /*------------- Zona del tab proveedor --------------*/

    /* Zona de ver provedor*/
    @FXML
    private void prodVer(ActionEvent event) {
        loadCenter("AñadirProveedor");
    }

    /* Zona de añadir provedor*/
    @FXML
    private void provedAña(ActionEvent event) {
        loadCenter("AnadirProveedor");
    }

    @FXML
    private void limpiarAddProveedor(ActionEvent event) {
        nombreProveedor.setText("");
        direccionProveedor.setText("");

    }

    @FXML
    private void guardarProveedor(ActionEvent event) {
        System.out.println( nombreProveedor.getText());
        System.out.println( direccionProveedor.getText());
    }

    /* Zona de mod provedor*/
    @FXML
    private void provedMod(ActionEvent event) {
        loadCenter("AnadirProveedor");
    }

    /* Zona de eliminar provedor*/
    @FXML
    private void provedEli(ActionEvent event) {
        loadCenter("AnadirProveedor");


    }



    /*------------- Zona del tab producto --------------*/

    private void loadCenter(String nombreFxml) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(nombreFxml + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);
    }

    private void setFechaYHora() {
        timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            labelBotDer.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }




}
