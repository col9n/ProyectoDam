package proyecto.modelos;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashSet;


public class Os {

    private final StringProperty name   = new SimpleStringProperty();
    private final BooleanProperty delete = new SimpleBooleanProperty();

    public Os( String nm, boolean del ) {
        name  .set( nm  );
        delete.set( del );
    }

    public StringProperty  nameProperty  () { return name;   }
    public BooleanProperty deleteProperty() { return delete; }
}

