package application;


import data.Book;
import data.CustomVBox;
import data.Product;
import data.ProductVBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class App extends Application {
	
	Stage window;	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) { // Stage is the same as document/window in HTML
		try {
			window = primaryStage;
			primaryStage.setTitle("lab 04 tables and menus");
			BorderPane layout = new BorderPane();
			Scene scene = new Scene(layout, 640, 400);

			Menu fileMenu = new Menu("File");
			MenuItem productsMenu = new MenuItem("Manage Products");
			fileMenu.getItems().add(productsMenu);
			MenuItem booksMenu = new MenuItem("Manage Books");
			fileMenu.getItems().add(booksMenu);
			fileMenu.getItems().add(new SeparatorMenuItem());
			MenuItem exitMenu = new MenuItem("Exit");
			fileMenu.getItems().add(exitMenu);
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(fileMenu);
			
			productsMenu.setOnAction(e -> {
				displayProductsTable(layout);
			});
			
		    booksMenu.setOnAction(e -> {
				displayBooksTable(layout);
		    });
		    
		    exitMenu.setOnAction(e -> {
		    	layout.setCenter(null);
		    });
			
			layout.setTop(menuBar);
			
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void displayBooksTable(BorderPane layout) {
		layout.setCenter(new CustomVBox<Book, String>(Book.class));
	}

	private void displayProductsTable(BorderPane layout) {
		layout.setCenter(new CustomVBox<Product, String>(Product.class));
	}

}
