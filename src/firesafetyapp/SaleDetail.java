package firesafetyapp;

import Database.DBConnect;
import Entity.Sale;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import java.time.LocalDate;
import javafx.scene.control.CheckBox;

import static firesafetyapp.Handler.*;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.StringConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;

public class SaleDetail {

    DBConnect database = new DBConnect();
    private Sale sale;
    private heading headingobj = new heading();
    String string = "Enter Sale Detail :";
    BigDecimal mul = new BigDecimal("0.00");
    BigDecimal val= new BigDecimal("0.00");
    BigDecimal totalval = new BigDecimal("0.00");
    BigDecimal tval = new BigDecimal("0.00");
    LocalDate date;

    
    String[] names = new String[]{"1 KG", "2 KG", "4KG", "5KG", "6KG", "9KG"};
    String[] qty = new String[names.length];
    String[] amt = new String[names.length];
    
    private CheckBox[] size = new CheckBox[names.length];
    private TextField[] Qty = new TextField[names.length];
    private TextField[] amount = new TextField[names.length];
    
    private Label Billnolabel = new Label("Bill No ");
    private TextField Billnotf = new TextField();

    private Label startdatelabel = new Label("Renew Date");
    private DatePicker startdate = new DatePicker();

    private Label salecustomerlabel = new Label("Select Customer");
    private ComboBox<String> customerlist = new ComboBox<>();

    private Label nextdatelabel = new Label("Next Renew Date");
    private TextField nextdate = new TextField();

    private Label selectsize = new Label("Select Size :");
    private Label qtyLabel = new Label("Qty :");
    private Label amountLabel = new Label("Amout :");

    private Label totalAmount = new Label("Total Amount :");
    private TextField totalamounttf = new TextField();

    private Button enterButton = new Button("Enter");
    private Button backButton = new Button("Back");
    private Button clearButton = new Button("clear");
    
    public SaleDetail(){
        reset();
       customerlist.getItems().addAll(DBConnect.storename());
    }

    public GridPane addSaledetail() {

        for (int i = 0; i < names.length; i++) {
            CheckBox cb = size[i] = new CheckBox(names[i]);
            String str = names[i] = new String();
            TextField qtytf = Qty[i] = new TextField();
            TextField amounttf = amount[i] = new TextField();

            amounttf.setDisable(true);
            qtytf.setDisable(true);
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                    for (int k = 0; k < names.length; k++) {
                        if (size[k].isSelected() == true) {
                            Qty[k].setDisable(false);
                            amount[k].setDisable(false);
                        } else {
                            Qty[k].setDisable(true);
                            amount[k].setDisable(true);
                            amount[k].setText("");
                        }
                    }
                }
            });
        }

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.getStyleClass().addAll("background");

        gridPane.add(headingobj.hBox(string), 0, 0, 2, 1);

        gridPane.add(Billnolabel, 0, 1);
        gridPane.add(Billnotf, 1, 1);

        gridPane.add(startdatelabel, 0, 2);
        gridPane.add(startdate, 1, 2);

        gridPane.add(salecustomerlabel, 0, 3);
        gridPane.add(customerlist, 1, 3);

        gridPane.add(nextdatelabel, 0, 4);
        gridPane.add(nextdate, 1, 4);

        gridPane.add(selectsize, 0, 5);
        gridPane.add(qtyLabel, 1, 5);
        gridPane.add(amountLabel, 2, 5);

        gridPane.add(size[0], 0, 6);
        gridPane.add(size[1], 0, 7);
        gridPane.add(size[2], 0, 8);
        gridPane.add(size[3], 0, 9);
        gridPane.add(size[4], 0, 10);
        gridPane.add(size[5], 0, 11);

        gridPane.add(Qty[0], 1, 6);
        gridPane.add(Qty[1], 1, 7);
        gridPane.add(Qty[2], 1, 8);
        gridPane.add(Qty[3], 1, 9);
        gridPane.add(Qty[4],1,10);
        gridPane.add(Qty[5],1,11);
        for (int j = 0; j < names.length; j++) {
            Qty[j].setPromptText("0");
        }

        gridPane.add(amount[0], 2, 6);
        amount[0].setText("0.00");
        gridPane.add(amount[1], 2, 7);
        amount[1].setText("0.00");
        gridPane.add(amount[2], 2, 8);
        amount[2].setText("0.00");
        gridPane.add(amount[3], 2, 9);
        amount[3].setText("0.00");
        gridPane.add(amount[4], 2, 10);
        amount[4].setText("0.00");
        gridPane.add(amount[5], 2, 11);
        amount[5].setText("0.00");
        
        gridPane.add(totalAmount, 0, 12);
        gridPane.add(totalamounttf, 1, 12);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(enterButton, backButton, clearButton);
        gridPane.add(vbox, 0, 13, 3, 1);

        customerlist.setEditable(true);
        startdate.setValue(LocalDate.now());

        startdate.setConverter(new StringConverter<LocalDate>(){
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
        //to get date from datepicker and print it on textfield
        startdate.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                date = startdate.getValue();
                nextdate.setText(DateConverter.setdate(date));
            }
        });
        
        //to set amount per qty
        
        // to print totalamount
        totalamounttf.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                
                //threekg=0.00,fivekg=0.00,ninekg=0.00;
                for (int k = 0; k < names.length; k++) {
                    if (size[k].isSelected() == true) {
                        
                        tval =BigDecimal.valueOf(Double.valueOf(amount[k].getText()));
                        totalval = totalval.add(tval);
                    
                }
                }
                totalamounttf.setText(String.valueOf(totalval));
                totalval= new BigDecimal("0.00");   
            }
        });

        enterButton.setOnAction(e -> {
            for (int j = 0; j < names.length; j++) {
                if (size[j].isSelected() == true) {
                    qty[j] = Qty[j].getText();
                    amt[j] = amount[j].getText();
                } else {
                    qty[j] = null;
                    amt[j] = null;
                }
            }
            
            //ensure validity of sale data
            //sale = new Sale();
            sale = validateSale(Billnotf, date, customerlist, nextdate, qty[0], qty[1], qty[2], qty[3],qty[4],qty[5],amt[0],amt[1],amt[2],amt[3],amt[4],amt[5], totalamounttf);

            if (sale != null) {
                database.enterSale(sale);
                new PopUp("sucsessfull", "data added successfully").alert();
                reset();
            } else {
                new PopUp("faild", "data addition unsuccessfull").alert();
            }
        });
        
        //clear the data from form
        clearButton.setOnAction(event -> {
            reset();
        });

        return gridPane;

    }
    public void reset(){
        Billnotf.clear();
        startdate.setValue(LocalDate.now());
        customerlist.setValue(null);
        nextdate.clear();
        for (int j = 0; j < names.length; j++) {
                //size[j].setSelected(false);
                //amount[j].clear();
                //Qty[j].clear();
            }
        totalamounttf.clear();
        
    }

}
