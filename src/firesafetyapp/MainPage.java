package firesafetyapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage extends Application {
    
    Stage Window;
    
    private CustomerDetail customerdetailform = new CustomerDetail(this);
    private SaleDetail saledetailform = new SaleDetail(this);
    //basic gui items
    private Button Customerdetailprompt = new Button("Customer Details");
    private Button SaleEntryprompt = new Button("Sale Entry");
    @Override
    public void start(Stage primaryStage) {
        
        Window = primaryStage;
        
        VBox  vBox = new VBox(10);
        vBox.getChildren().addAll(Customerdetailprompt,SaleEntryprompt);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox,450,500);
        
        //poper closing for this class
        primaryStage.setOnCloseRequest(e ->{
            
            e.consume();
            closeProgram();
        });
        
        //option for enter new customer
        Customerdetailprompt.setOnAction(event ->{
            try
            {
                customerdetailform.start(primaryStage);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println(e);
            }
            });
        
        //option for enter new sale entry
        SaleEntryprompt.setOnAction(event ->{
            try
            {
                saledetailform.start(primaryStage);
            }
            catch (Exception e){
                e.printStackTrace();
            }
                
        });
               
        

        primaryStage.setTitle("Choice Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
                }

    /**
     * @param args the command line arguments
     */
   

    private void closeProgram() {
        
        ConfirmBox cb = new ConfirmBox();
        
        boolean answer = cb.alert();
        
        if(answer){
            Window.close();
        }
    }
    public static void main(String[] args) {
        
        launch(args);
        
    }         
}
