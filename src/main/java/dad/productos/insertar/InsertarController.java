package dad.productos.insertar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class InsertarController implements Initializable {
	
	// view
	
    @FXML
    private CheckBox congeladoCheck;

    @FXML
    private TextField denoText;

    @FXML
    private ComboBox<?> familiaCombo;

    @FXML
    private TextField observacionText;

    @FXML
    private TextField precioText;

    @FXML
    private BorderPane view;
	
	public InsertarController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InsertarView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO inicializar el controlador
	}
	
	public BorderPane getView() {
		return view;
	}

    @FXML
    void onCancelar(ActionEvent event) {
    	// TODO volver al menú sin guardar los cambios
    }

    @FXML
    void onGuardar(ActionEvent event) {
    	// TODO guardar los cambios y volver al menú
    }

}
