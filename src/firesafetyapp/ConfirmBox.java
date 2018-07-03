package firesafetyapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    
       private boolean answer;
       
       //basic gui item
       Label messagelabel = new Label("are you sure you want to exit?");
       Button staybutton = new Button("Stay");
       Button closebutton = new Button("Close");
       
       //prompt the user choose if they really want to exit
       public boolean alert(){
           
           Stage window = new Stage();
           
           window.initModality(Modality.APPLICATION_MODAL);
           window.setTitle("Exit");
           
           VBox vBox = new VBox(10);
           vBox.setAlignment(Pos.CENTER);
           vBox.getChildren().addAll(messagelabel,staybutton,closebutton);
           
           Scene scene = new Scene(vBox,400,200);
           
           closebutton.setOnAction(e ->{
                answer = true;
                window.close();
           });
           
           staybutton.setOnAction(event ->{
               answer = false;
               window.close();
           });
           window.setScene(scene);
           window.showAndWait();
           
           return answer;
       }     
    }
