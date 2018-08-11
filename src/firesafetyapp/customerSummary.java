package firesafetyapp;

import Database.DBConnect;
import Entity.Customer;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Insets;

public class customerSummary {
    
    DBConnect database = new DBConnect();
    GridPane root =null;
        
    TableView<Customer> table ;
        
    TableColumn shopName = new TableColumn("Customer Name");
    TableColumn shopContact = new TableColumn("Contact ");
    TableColumn shopAddress = new TableColumn("Address ");
    TableColumn area = new TableColumn("Area");
    TableColumn city = new TableColumn("City");
    TableColumn zipcode = new TableColumn("zipcode");
    
    public GridPane getCustomerTable(){
        root = new GridPane();
        table = new TableView();
        table.getColumns().addAll(shopName,shopContact,shopAddress,area,city,zipcode);
        
        root.setPadding(new Insets(5));
        ColumnConstraints c1 =new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(c1);
        RowConstraints r1 = new RowConstraints();
        r1.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(r1);
        root.add(table,0,0);
        shopName.setMinWidth(200);
        shopContact.setMinWidth(200);
        shopAddress.setMinWidth(250);
        area.setMinWidth(100);
        city.setMinWidth(100);
        zipcode.setMinWidth(100);
        shopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        shopContact.setCellValueFactory(new PropertyValueFactory<>("shopCont"));
        shopAddress.setCellValueFactory(new PropertyValueFactory<>("streetAdd"));
        area.setCellValueFactory(new PropertyValueFactory<>("area"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
          
        table.setItems(database.getCustomerList());
        
        return root; 
                
    }

}
