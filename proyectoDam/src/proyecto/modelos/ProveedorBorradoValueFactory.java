package proyecto.modelos;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;



public class ProveedorBorradoValueFactory implements Callback<TableColumn.CellDataFeatures<Proveedor, CheckBox>, ObservableValue<CheckBox>> {
    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Proveedor, CheckBox> param) {
        Proveedor person = param.getValue();
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().setValue(person.isBorradoLogico());
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
            person.setBorradoLogico(new_val);
        });
        return new SimpleObjectProperty<>(checkBox);
    }
}