package firesafetyapp;

import Entity.Customer;
import Entity.Sale;
import java.math.BigDecimal;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Handler {

    //validate agent and ensure completeness
    public static Customer validateCustomer(TextField shopname, TextField shopcontact, TextField streetadd, ComboBox area, TextField city, TextField zip) {
        Customer customer = null;

        if (shopname.getText().isEmpty() || shopcontact.getText().isEmpty() || streetadd.getText().isEmpty()
                || city.getText().isEmpty()
                || zip.getText().isEmpty() || area.getValue() == null) {
            new PopUp("Name Error", "Name or adress field is empty").alert();
        } else if (/*!shopname.getText().matches("[A-Z][a-zA-Z]*") ||*/ !shopcontact.getText().matches("\\d{10}")
                || /*!streetadd.getText().matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)") || !city.getText().matches("([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+)") ||*/ !zip.getText().matches("\\d{5}")) {
            new PopUp("name error", "field not match").alert();
        } else {
            customer = new Customer();

            //customer.setId(new SecureRandom().nextInt(99999999) + 100000000);
            customer.setShopName(shopname.getText());
            customer.setShopCont(Long.valueOf(shopcontact.getText()));
            customer.setStreetAdd(streetadd.getText());
            customer.setArea(area.getValue().toString());
            customer.setCity(city.getText());
            customer.setZipcode(Integer.valueOf(zip.getText()));
        }

        return customer;
    }

    //validate sale and ensure completeness
    public static Sale validateSale(TextField billno, LocalDate startdate, ComboBox customername, TextField nextdate, String onekgQty, String threekgQty, String fivekgQty, String ninekgQty,String onekgAmt,String threekgAmt,String fivekgAmt,String ninekgAmt, TextField amount) {
        Sale sale = null;
        try {
            if (billno.getText().isEmpty() || startdate == null || customername.getValue() == null || nextdate.getText().isEmpty() || amount.getText().isEmpty()) {
                new PopUp("Error", "some Field is empty").alert();
            } else {
                sale = new Sale();
                Date ndd = new SimpleDateFormat("dd-MM-yyyy").parse(nextdate.getText());
                java.sql.Date nd = new java.sql.Date(ndd.getTime());
                //sale.setsaleid(new SecureRandom().nextInt(99999999) + 100000000);
                sale.setBillno(Integer.valueOf(billno.getText()));
                sale.setStartdate(java.sql.Date.valueOf(startdate));
                sale.setShopname(customername.getValue().toString());
                sale.setNextdate(nd);
                if (onekgQty == null) {
                    onekgQty = "0";
                }
                if (threekgQty == null) {
                    threekgQty = "0";
                }
                if (fivekgQty == null) {
                    fivekgQty = "0";
                }
                if (ninekgQty == null) {
                    ninekgQty = "0";
                }
                sale.setOnekgqty(Integer.valueOf(onekgQty));
                sale.setThreekgqty(Integer.valueOf(threekgQty));
                sale.setFivekgqty(Integer.valueOf(fivekgQty));
                sale.setNinekgqty(Integer.valueOf(ninekgQty));
                if (onekgAmt == null){
                    onekgAmt = "0.00";
                }
                if (threekgAmt == null){
                    threekgAmt = "0.00";
                }
                if (fivekgAmt == null){
                    fivekgAmt = "0.00";
                }
                if (ninekgAmt == null){
                    ninekgAmt = "0.00";
                }
                sale.setonekgAmount(BigDecimal.valueOf(Double.valueOf(onekgAmt)));
                sale.setthreekgAmount(BigDecimal.valueOf(Double.valueOf(threekgAmt)));
                sale.setfivekgAmount(BigDecimal.valueOf(Double.valueOf(fivekgAmt)));
                sale.setninekgAmount(BigDecimal.valueOf(Double.valueOf(ninekgAmt)));
                sale.setAmount(BigDecimal.valueOf(Double.valueOf(amount.getText())));

            }
        } catch (ParseException error) {
            error.printStackTrace();
        }
        return sale;
    }

    //method to properly close the program
    public static void closeProgram(Stage window) {

        ConfirmBox cb = new ConfirmBox();

        boolean answer = cb.alert();

        if (answer) {

            //*************************
            //do a routine closing process
            //*************************
            window.close();
        }
    }

    public static List<String> area() {
        List<String> area = new ArrayList<>();
        
        area.add("Mulund");
        area.add("Nahur");
        area.add("Bhandup");
        area.add("Kanjurmarg");
        area.add("Vikhroli");
        area.add("Ghatkopar");

        Collections.sort(area);
        return area;

    }

    public static Rectangle2D getWindowsize() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

        return visualBounds;
    }

}
