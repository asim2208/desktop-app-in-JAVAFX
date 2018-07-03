/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firesafetyapp;

import Database.DBConnect;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import java.time.LocalDate;

import firesafetyapp.DateConverter;
import static firesafetyapp.Handler.*;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

/**
 *
 * @author Asim Patel
 */
public class SaleDetail extends Application {
    private MainPage mainpage;
    
    private Label startdatelabel = new Label("Renew Date");
    private DatePicker startdate = new DatePicker();
    
    private Label salecustomerlabel = new Label ("Select Customer");
    private ComboBox<String> customerlist = new ComboBox<>();
  
    private Label nextdatelabel = new Label("Next Renew Date");
    private TextField nextdate = new TextField();
    private Button enterButton = new Button ("Enter");
    private Button backButton = new Button("Back");
    private Button clearButton= new Button ("clear");
    
    public SaleDetail(MainPage mainpage){
        this.mainpage = mainpage;
        
        customerlist.getItems().addAll(DBConnect.storename());
    }
    
    
    @Override
    public void start (Stage primaryStage)throws Exception{
        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10,10,10,10));
        
        vbox.getChildren().addAll(startdatelabel,startdate,salecustomerlabel,customerlist,nextdatelabel,nextdate,enterButton,backButton,clearButton);
        vbox.setAlignment(Pos.TOP_LEFT);
        
        customerlist.setEditable(true);
        startdate.setValue(LocalDate.now());
        startdate.setConverter(new StringConverter<LocalDate>(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        @Override
        public String toString(LocalDate date){
            if (date!=null){
                return dateformatter.format(date);
            }
            else{
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string){
            if(string!= null && !string.isEmpty()){
                return LocalDate.parse(string, dateformatter);
            }
            else{
                return null;
            }
            
        }
    });
        
        Scene scene = new Scene(vbox,600,475);
        
        primaryStage.setOnCloseRequest( e ->{
            
            e.consume();
            closeProgram(primaryStage);
        });
    
    primaryStage.setTitle("Sale Detail Entry");
    primaryStage.setScene(scene);
    primaryStage.show();
            }

}
