package dad.productos.listar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aed.productos.dao.ProductoDAO;
import aed.productos.entities.Familia;
import aed.productos.entities.Observacion;
import aed.productos.entities.Producto;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;

public class ProductosController implements Initializable {
	
	// actions
	
	private EventHandler<ActionEvent> onBack;
	
	// model
	
	private ListProperty<Producto> productos = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	// view

    @FXML
    private TableColumn<Producto, Number> codigoColumn;

    @FXML
    private TableColumn<Producto, Boolean> congeladoColumn;

    @FXML
    private TableColumn<Producto, String> denoColumn;

    @FXML
    private TableColumn<Producto, Familia> familiaColumn;

    @FXML
    private TableColumn<Producto, Observacion> observaColumn;

    @FXML
    private TableColumn<Producto, Number> precioColumn;

    @FXML
    private TableView<Producto> productsTable;

    @FXML
    private BorderPane view;
    
	public ProductosController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// configuramos los cell value factories de la tabla de productos (extraen las propiedades de los items de la tabla)
		
		codigoColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getCodProducto()));
		congeladoColumn.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isCongelado()));
		denoColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDenoProducto()));
		familiaColumn.setCellValueFactory(c -> new SimpleObjectProperty<Familia>(c.getValue().getFamilia()));
		observaColumn.setCellValueFactory(c -> new SimpleObjectProperty<Observacion>(c.getValue().getObservacion()));
		precioColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrecioBase()));
		
		// configura como se presentan los datos de las columnas (cell factories)
		
		congeladoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(congeladoColumn));

		// bindings
		
		productsTable.itemsProperty().bind(productos);
		
		// cargamos los productos
		
		productos.setAll(ProductoDAO.getProductos());
		
	}
	
	public BorderPane getView() {
		return view;
	}
	
    @FXML
    void onBack(ActionEvent event) {
    	if (onBack != null) onBack.handle(event);
    }
    
    public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
