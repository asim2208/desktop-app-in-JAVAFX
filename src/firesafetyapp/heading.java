package firesafetyapp;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class heading {
    public HBox hBox(String string){
    HBox hBox = new HBox(10);
    hBox.setPadding(new Insets(15, 12, 15, 12));
    hBox.setSpacing(10);
    hBox.getStyleClass().addAll("HBox");
    
    Text title = new Text(string);
    title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    title.setSmooth(true);
    title.setFill(Color.WHITE);
    
    title.getStyleClass().addAll("text");
    hBox.getChildren().addAll(title);
    
    return hBox;
    }
    
}
