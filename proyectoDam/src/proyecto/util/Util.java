package proyecto.util;

import javafx.scene.control.*;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.util.List;

public class Util {
    public static void alertaShow(String titulo, String texto, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public static Alert alertaGet(String titulo, String texto, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(texto);
        return alert;
    }

    public static void activacionBoton(TextField textField, TextField textField1, Button button){
       ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(textField, Validator.createEmptyValidator("Este campo no puede estar vacío"));
        textField.setPromptText("No puede estar vacio");
        validationSupport.registerValidator(textField1, Validator.createEmptyValidator("Este campo no puede estar vacío"));
        textField1.setPromptText("No puede estar vacio");
        button.disableProperty().bind(validationSupport.invalidProperty());

    }

    public static void activacionBotonComboBox(TextField textField, ComboBox textField1, Button button){
        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(textField, Validator.createEmptyValidator("Este campo no puede estar vacío"));
        textField.setPromptText("No puede estar vacio");
        validationSupport.registerValidator(textField1, Validator.createEmptyValidator("Este campo no puede estar vacío"));
        textField1.setPromptText("No puede estar vacio");
        button.disableProperty().bind(validationSupport.invalidProperty());

    }

    public static void enableButtonListaContine(List lista, Button button){
        button.setDisable(false);
        if(lista.size()==0)
            button.setDisable(true);
    }
}
