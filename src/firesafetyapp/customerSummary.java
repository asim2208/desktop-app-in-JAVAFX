package firesafetyapp;

import Entity.Customer;
import javafx.geometry.Insets;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;

public class customerSummary {
    
    TableView<Customer> table = new TableView<>();
        
    TableColumn<Customer,String> shopName = new TableColumn<>("Customer Name");
    TableColumn<Customer,Integer> shopContact = new TableColumn<>("Contact ");
    TableColumn<Customer,String> shopAddress = new TableColumn<>("Address ");
    TableColumn<Customer,String> area = new TableColumn<>("Area");
    TableColumn<Customer,String> city = new TableColumn<>("City");
    TableColumn<Customer,Integer> zipcode = new TableColumn<>("zipcode");
    
    public StackPane getCustomerTable(){
        
        StackPane root =new StackPane();
        table.getColumns().addAll(shopName,shopContact,shopAddress,area,city,zipcode);
        
        root.setPadding(new Insets(5));
        root.getChildren().add(table);
        
        return root; 
                
    }
}
