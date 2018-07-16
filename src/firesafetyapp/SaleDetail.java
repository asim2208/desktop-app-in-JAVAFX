package firesafetyapp;

import Database.DBConnect;
import Entity.Sale;

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
import javafx.scene.control.CheckBox;

import static firesafetyapp.Handler.*;
import java.time.format.DateTimeFormatter;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.StringConverter;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SaleDetail extends Application {

    private MainPage mainpage;
    DBConnect database = new DBConnect();
    private Sale sale;

    LocalDate date;
    
    String[] names = new String[]{"1 KG","3 KG","5KG","9KG"};
    CheckBox[] size = new CheckBox[names.length];

    private Label Billnolabel = new Label("Bill No ");
    private TextField Billnotf = new TextField();

    private Label startdatelabel = new Label("Renew Date");
    private DatePicker startdate = new DatePicker();

    private Label salecustomerlabel = new Label("Select Customer");
    private ComboBox<String> customerlist = new ComboBox<>();

    private Label nextdatelabel = new Label("Next Renew Date");
    private TextField nextdate = new TextField();

    private Label selectsize = new Label("Select Size :");
    
  

    private Label Amount = new Label("Amount");
    private TextField amounttf = new TextField();

    private Button enterButton = new Button("Enter");
    private Button backButton = new Button("Back");
    private Button clearButton = new Button("clear");

    public SaleDetail(MainPage mainpage) {
        this.mainpage = mainpage;

        customerlist.getItems().addAll(DBConnect.storename());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        for(int i=0; i< names.length; i++){
    CheckBox cb = size[i] = new CheckBox(names[i]);
    String str =names[i]=new String();
    cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
                    System.out.println(new_val+str+" is selected");
        }
    });
}
        
        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        vbox.getChildren().addAll(Billnolabel, Billnotf, startdatelabel, startdate, salecustomerlabel, customerlist, nextdatelabel,
                nextdate, selectsize, size[0], size[1], size[2], size[3], Amount, amounttf, enterButton, backButton, clearButton);
        vbox.setAlignment(Pos.TOP_LEFT);

        customerlist.setEditable(true);
        startdate.setValue(LocalDate.now());

        startdate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateformatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateformatter);
                } else {
                    return null;
                }

            }

        });
        //to get date from datepicker and print it on textfild
        startdate.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                date = startdate.getValue();
                nextdate.setText(DateConverter.setdate(date));
            }
        });
        
        

        Scene scene = new Scene(vbox, 700, 700);

        primaryStage.setOnCloseRequest(e -> {

            e.consume();
            closeProgram(primaryStage);
        });

       /* enterButton.setOnAction(e -> {
            //ensure validity of sale data
            sale = validateSale(Billnotf, date, customerlist, nextdate, amounttf);

            if (sale != null) {
                database.enterSale(sale);
                new PopUp("sucsessfull", "data added successfully").alert();
                Billnotf.clear();
                nextdate.clear();
                size1.setSelected(false);
                size2.setSelected(false);
                size3.setSelected(false);
                size4.setSelected(false);
                amounttf.clear();
            }
        });*/
        //returns to mainpage
        backButton.setOnAction(e -> {
            try {
                mainpage.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //clear the data from form
        clearButton.setOnAction(event -> {

            Billnotf.clear();
            nextdate.clear();
            for(int j=0; j<names.length;j++){
            size[j].setSelected(false);
            }
            amounttf.clear();
        });

        primaryStage.setTitle("Sale Detail Entry");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
