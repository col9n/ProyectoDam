package proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyecto.views.LoginController;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("views/Login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController loginController = (LoginController) fxmlLoader.getController();
        loginController.setStage(primaryStage);
        primaryStage.show();
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();

        /*
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                controllerCorreo.cerrarReloj();
            }
        });

         */
    }






    public static void main(String[] args) {
        launch(args);
    }
}