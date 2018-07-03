package firesafetyapp;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static firesafetyapp.Handler.closeProgram;
import javafx.geometry.Pos;
import javafx.stage.Modality;


//basic popup window for errors encountered during application process
public class PopUp {
    //title of window
    private String title;
    //message of the error
    private String message;
    
    //basic GUI items
    Label messagelabel = new Label();
    Button closebutton = new Button("Got it!");
    
    //class constructor
    public PopUp(String title, String message){
        this.title = title;
        this.message = message;
    }
    
    //alert the usr of the error
    public void alert(){
        Stage window = new Stage();
        
        
        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle(title);
        
        messagelabel.setText(message);
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(messagelabel,closebutton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,message.length()*10 ,100);
        
        //properly closes program
        window.setOnCloseRequest(e -> closeProgram(window));
        
        closebutton.setOnAction(e -> window.close());
        
        window.setScene(scene);
        window.showAndWait();
    }
}
