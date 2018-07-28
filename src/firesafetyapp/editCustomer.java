package firesafetyapp;

import Database.DBConnect;
import Entity.Customer;
import static firesafetyapp.Handler.*;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class editCustomer {
    
    DBConnect database = new DBConnect();
    Customer customer;
    String customerName = null;
    
    private heading headingobj = new heading();
    String string = "Edit Customer Detail :";
    private ComboBox <String> selectCustomer = new ComboBox<>();
    private Label selectCustomerlabel = new Label("Select Customer :");
    private Button editButton = new Button("Edit");
    
    private Label shopNamelabel = new Label("Shop Name :");
    private TextField shopNametf = new TextField();
     
    private Label shopContactlabel = new Label("Contact Number :");
    private TextField shopContacttf = new TextField();
    
    private Label streetAddlabel = new Label("Street :");
    private TextField streetAddtf =  new TextField();
    
    private Label areaLabel = new Label("area");
    private ComboBox<String> areaCombobox = new ComboBox<>();
    
    private Label cityLabel = new Label("City");
    private TextField cityTf = new TextField();
    
    private Label zipLabel = new Label("ZipCode");
    private TextField zipTf = new TextField();
    private Button updateButton = new Button("Update");
   
    public editCustomer(){
    selectCustomer.getItems().addAll(DBConnect.storename());
    }
    public GridPane addGridpane(){
        
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.getStyleClass().addAll("background");
        gridPane.setVgap(10);
        gridPane.setHgap(30);
        
        int gridsize = 6;
        for(int i=0;i<=gridsize;i++)
        {
            RowConstraints rowCon = new RowConstraints();
            //rowCon.setPercentHeight(gridsize);
            gridPane.getRowConstraints().add(rowCon);
        }
        
        gridPane.add(headingobj.hBox(string),0,0,2,1);
        gridPane.add(selectCustomerlabel, 0, 1);
        gridPane.add(selectCustomer, 1, 1);
        gridPane.add(editButton, 2, 1);
        gridPane.add(shopNamelabel,0,2);
        gridPane.add(shopContactlabel,3,2);
        gridPane.add(shopNametf, 0, 3,2,1);
        gridPane.add(shopContacttf,3,3,2,1);
        gridPane.add(streetAddlabel,0,4);
        gridPane.add(streetAddtf,0,5,2,1);
        gridPane.add(areaLabel,0,6);
        gridPane.add(cityLabel,1,6);
        gridPane.add(zipLabel,2,6);
        gridPane.add(areaCombobox,0,7);
        gridPane.add(cityTf,1,7);
        gridPane.add(zipTf,2,7);
        gridPane.add(updateButton,0,9);
    
    
 
        
    editButton.setOnAction(e->{
        
        customerName = selectCustomer.getValue();
        
        editCustomer(customerName);
    });
    
    updateButton.setOnAction(e->{
        customer = validateCustomer(shopNametf,shopContacttf,streetAddtf,areaCombobox,cityTf,zipTf);
        
        if(customer != null){
            database.updateCustomer(customer,customerName);
            new PopUp("sucsessfull","data updated successfully").alert();
            reset();
        }
    });
        
            return gridPane;
    }
    public ArrayList<String> editCustomer(String customername){
        //System.out.println(customerName);
        
        ArrayList<String> list = new ArrayList<>();
        list= DBConnect.getCustomer(customername);
        
        if (customername.equals(list.get(0))){
            shopNametf.setText(list.get(0));
            shopContacttf.setText(list.get(1));
            streetAddtf.setText(list.get(2));
            areaCombobox.setValue(list.get(3));
            cityTf.setText(list.get(4));
            zipTf.setText(list.get(5));
        }
        return list;
    }
    public void reset(){
        selectCustomer.setValue(null);
        shopNametf.clear();
        shopContacttf.clear();
        streetAddtf.clear();
        areaCombobox.setValue(null);
        cityTf.clear();
        zipTf.clear();
    }
}
