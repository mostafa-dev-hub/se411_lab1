package data;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;

public class CustomVBox<T, E> extends VBox{
	
	TableView<T> productsTable;
	TextField nameInput, priceInput, quantityInput;
	TableColumn<T, String> nameColumn;
	TableColumn<T, Double> priceColumn;
	TableColumn<T, E> quantityColumn;
	Button deleteButton;
	Button addButton;
	HBox btnLayout;
	VBox layout;
	Label label;
		
	public CustomVBox(Class<T> type) {
			label = new Label("Manage "+ type.getSimpleName() + "s");
		  	nameColumn = new TableColumn<T, String>("Name");
			nameColumn.setMinWidth(140);
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			
		    priceColumn = new TableColumn<T, Double>("Price");
			priceColumn.setMinWidth(100);
			priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
			
			quantityInput = new TextField();

			
			quantityColumn = new TableColumn<T, E>("quantity");
			if(type == Product.class) {
				quantityColumn.setText("quantity");
				quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				quantityInput.setPromptText("quantity");

			}
			else {
				quantityColumn.setText("author");
				quantityColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
				quantityInput.setPromptText("author");
			}
			quantityColumn.setMinWidth(100);
			
			productsTable = new TableView<T>(); 
			productsTable.setItems((ObservableList<T>) getItems(type));
			productsTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
			
			nameInput = new TextField();
			nameInput.setPromptText("name");
			nameInput.minWidth(100);
			
			priceInput = new TextField();
			priceInput.setPromptText("price");
			
			
			
			Button addButton = new Button("Add");
			addButton.setOnAction(e -> {
				addButtonAction(type);
			});
			
			
			Button deleteButton = new Button("Delete");
			deleteButton.setOnAction(e -> {
				deleteButtonAction();
			});
			
			btnLayout = new HBox();
			btnLayout.setPadding(new Insets(10, 20, 10, 20));
			btnLayout.setSpacing(10);
			btnLayout.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);
			
			layout = new VBox(10);
			layout.setPadding(new Insets(10, 10, 10, 10));
			layout.getChildren().addAll(label,productsTable, btnLayout);
			
			this.setPadding(new Insets(10));
			this.setSpacing(10);
			this.getChildren().addAll(label, productsTable, btnLayout);
	}
  
	public void deleteButtonAction() {
		ObservableList<T> productSelected, allProducts;
		allProducts = productsTable.getItems();
		productSelected = productsTable.getSelectionModel().getSelectedItems();
		
		for(T p : productSelected ) {
			allProducts.remove(p);
		}
	}


	public <T> void addButtonAction(Class<T> type) {
		 if (type == Product.class) {
			 addProductAction();
		 }
		 else {
			 addBookAction();
		 }
	}
	
	public void addProductAction() {
	    Product product = new Product();
	    product.setName(nameInput.getText());
	    product.setPrice(Double.parseDouble(priceInput.getText()));
	    product.setQuantity(Integer.parseInt(quantityInput.getText()));
	    productsTable.getItems().add((T) product);
	}

	public void addBookAction() {
	    Book book = new Book();
	    book.setName(nameInput.getText());
	    book.setPrice(Double.parseDouble(priceInput.getText()));
	    book.setAuthor(String.valueOf(quantityInput.getText()));
	    productsTable.getItems().add((T) book);
	}


	public <T> ObservableList<T> getItems(Class<T> type) {
	    ObservableList<T> items = FXCollections.observableArrayList();

	    if (type == Product.class) {
	        items.add((T) new Product("Laptop", 750, 100));
	        items.add((T) new Product("Chair", 50, 30));
	        items.add((T) new Product("Table", 150, 20));
	        items.add((T) new Product("Screen", 250, 80));
	        items.add((T) new Product("Charger", 15, 1000));
	        items.add((T) new Product("Lamp", 10, 1200));
	    } else if (type == Book.class) {
	        items.add((T) new Book("Harry Potter", 750, "Ahmad"));
	        items.add((T) new Book("Toy Story", 50, "Ali"));
	        items.add((T) new Book("Avemgaers", 150, "Mustrafa"));
	        items.add((T) new Book("Helloo", 250, "Omar"));
	        items.add((T) new Book("Book 1212", 15, "Mohammad"));
	        items.add((T) new Book("Book 12121", 10, "Noor"));
	    }

	    return items;
	}



	

}
