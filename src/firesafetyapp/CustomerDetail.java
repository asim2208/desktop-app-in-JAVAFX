package firesafetyapp;

import Database.DBConnect;
import Entity.Customer;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import static firesafetyapp.Handler.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CustomerDetail {
    
    DBConnect database = new DBConnect();
    
    private MainPage mainpage;
    private Customer customer;
    private heading headingobj = new heading(); 
    Stage window;
    String string = "Enter Customer Detail :";
    //basic gui item
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
   
    private Button enterButton = new Button("Enter");
    private Button backButton = new Button("Back");
    private Button clearButton = new Button("Clear");
    //private Stage primaryStage;
    
    public CustomerDetail(){
       areaCombobox.getItems().addAll(area());
        
    }
    
    
    public GridPane addCustomerdetail() {
       
       GridPane gridLayout = new GridPane();
       gridLayout.setHgap(30);
       //gridLayout.setVgap(1);
       //gridLayout.setGridLinesVisible(true);
       gridLayout.getStyleClass().addAll("background");
       int numRow = 7;
       for (int i=0;i<=11;i++){
           RowConstraints rowCons= new RowConstraints();
           //rowCons.setPercentHeight(numRow);
           gridLayout.getRowConstraints().add(rowCons);
       }
       shopNametf.setPrefColumnCount(30);
       streetAddtf.setPrefColumnCount(30);
       shopContacttf.setPrefColumnCount(30);
      
       gridLayout.setPadding(new Insets(10,10,10,10));
       gridLayout.add(shopNamelabel,0,1);
       gridLayout.add(shopContactlabel,2,1);
       gridLayout.add(shopNametf,0,2,2,1);
      
       gridLayout.add(shopContacttf,2,2,2,1);
       gridLayout.add(streetAddlabel,0,3);
       gridLayout.add(streetAddtf,0,4,2,1);
       
       gridLayout.add(areaLabel,0,5);
       gridLayout.add(cityLabel,1,5);
       gridLayout.add(zipLabel,2,5);
  
       gridLayout.add(areaCombobox,0,6);
       gridLayout.add(cityTf,1,6);
       gridLayout.add(zipTf,2,6);
       
       HBox hBox = new HBox(10);
       hBox.setPadding(new Insets(10,10,10,10));
       hBox.getChildren().addAll(enterButton,clearButton,backButton);
       gridLayout.add(hBox,0,7,2,3);
       gridLayout.setAlignment(Pos.TOP_LEFT);
       
       gridLayout.add(headingobj.hBox(string),0,0,3,1);
       
       //returns to mainpage
       
     /* backButton.setOnAction(e ->{
           try{
               mainpage.start(primaryStage);
           }
           catch(Exception e1){
               e1.printStackTrace();
           }
       });*/
       
       //enter customer data
       enterButton.setOnAction(e ->{
           
           //ensure validity of data and generates and customer
           customer = validateCustomer(shopNametf,shopContacttf,streetAddtf,areaCombobox,cityTf,zipTf);
           if (customer != null){
               database.enterCustomer(customer);
               new PopUp("sucsessfull","data added successfully").alert();
               reset();
           }      
                   
       });
       
       //clear the data from form
       clearButton.setOnAction(event ->{
           
           reset();
       });
       return gridLayout;
    }
    public void reset(){
               shopNametf.clear();
               shopContacttf.clear();
               streetAddtf.clear();
               areaCombobox.setValue(null);
               cityTf.clear();
               zipTf.clear();
    }
}
