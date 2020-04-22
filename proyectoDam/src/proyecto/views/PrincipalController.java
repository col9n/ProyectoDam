package proyecto.views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import proyecto.Logica.Logica;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PrincipalController implements Initializable {
    private Stage stage=new Stage();
    private Timeline timeline=Logica.getInstance().getTimeline();

    @FXML
    private Label labelBotIzq;

    @FXML
    private Label labelBotDer;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelBotIzq.setText(Logica.getInstance().getUsuario().toString());
        setFechaYHora();


    }

    private void setFechaYHora() {
        timeline= new Timeline(new KeyFrame(Duration.ZERO, e -> {
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
