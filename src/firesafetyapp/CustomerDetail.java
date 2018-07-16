package firesafetyapp;

import Database.DBConnect;
import Entity.Customer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import static firesafetyapp.Handler.*;

public class CustomerDetail extends Application {
    
    DBConnect database = new DBConnect();
    
    private MainPage mainpage;
    private Customer customer;
    Stage window;
    //basic gui item
    private Label Shopnamelabel = new Label("Shop Name :");
    private TextField shopnametf = new TextField();
     
    private Label Shopcontactlabel = new Label("Contact Number :");
    private TextField Shopcontacttf = new TextField();
    
      private Label StreetAddlabel = new Label("Street");
    private TextField StreetAddtf =  new TextField();
    
    private Label Arealabel = new Label("area");
    private ComboBox<String> Areacombobox = new ComboBox<>();
    
    private Label Citylabel = new Label("City");
    private TextField Citytf = new TextField();
    
    private Label Ziplabel = new Label("ZipCode");
    private TextField Ziptf = new TextField();
   
    private Button Enterbutton = new Button("Enter");
    private Button Backbutton = new Button("Back");
    private Button Clearbutton = new Button("Clear");
    
    public CustomerDetail(MainPage mainpage){
        this.mainpage = mainpage;
        Areacombobox.getItems().addAll(area());
    }
    
    @Override
    public void start(Stage primaryStage) {
       VBox vBoxLayout = new VBox (10);
       vBoxLayout.setPadding(new Insets(10,10,10,10));
       
       vBoxLayout.getChildren().addAll(Shopnamelabel,shopnametf,Shopcontactlabel,Shopcontacttf,
               StreetAddlabel,StreetAddtf,Arealabel,Areacombobox,Citylabel,Citytf,Ziplabel,Ziptf,                         
               Enterbutton,Clearbutton,Backbutton);
       vBoxLayout.setAlignment(Pos.TOP_LEFT);
       Scene scene = new Scene(vBoxLayout,600,500);
       
       //static closing of progeram
       primaryStage.setOnCloseRequest(e ->{
           
           e.consume();
           closeProgram(primaryStage);
       });
       
       //returns to mainpage
       
       Backbutton.setOnAction(e ->{
           try{
               mainpage.start(primaryStage);
           }
           catch(Exception e1){
               e1.printStackTrace();
           }
       });
       
       //enter customer data
       Enterbutton.setOnAction(e ->{
           
           //ensure validity of data and generates and customer
           customer = validateCustomer(shopnametf,Shopcontacttf,StreetAddtf,Areacombobox,Citytf,Ziptf);
           if (customer != null){
               database.enterCustomer(customer);
               new PopUp("sucsessfull","data added successfully").alert();
               shopnametf.clear();
               Shopcontacttf.clear();
               StreetAddtf.clear();
               Citytf.clear();
               Ziptf.clear();
           }      
                   
       });
       
       //clear the data from form
       Clearbutton.setOnAction(event ->{
           
           shopnametf.clear();
           Shopcontacttf.clear();
           StreetAddtf.clear();
           Citytf.clear();
           Ziptf.clear();
       });
       
       primaryStage.setTitle("Customer Detail");
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
   
    
}
