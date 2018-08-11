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
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Handler {

    //validate agent and ensure completeness
    public static Customer validateCustomer(TextField shopname, TextField shopcontact, TextField streetadd, ComboBox area, TextField city, TextField zip) {
        Customer customer = new Customer();
        customer = null;

        if (shopname.getText().isEmpty() || shopcontact.getText().isEmpty() || streetadd.getText().isEmpty()
                || city.getText().isEmpty()
                || zip.getText().isEmpty() || area.getValue() == null) {
            new PopUp("Name Error", "Field is Empty").alert();
        } else if (!shopcontact.getText().matches("\\d{10}")) {
            new PopUp("Name Error", "Contact must be 10 Digit").alert();
        } else if (!zip.getText().matches("\\d{6}")) {
            new PopUp("Error", "Zip Code must be 6 Digit").alert();
        } else {
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
    public static Sale validateSale(TextField billno, LocalDate startdate, ComboBox customername, TextField nextdate, String onekgQty, String twokgQty, String fourkgQty, String fivekgQty, String sixkgQty, String ninekgQty, String onekgAmt, String twokgAmt, String fourkgAmt, String fivekgAmt, String sixkgAmt, String ninekgAmt, TextField amount) {
        Sale sale = new Sale();
        try {
            if (billno.getText().isEmpty() || startdate == null || customername.getValue() == null || nextdate.getText().isEmpty() || amount.getText().isEmpty()) {
                new PopUp("Error", "Field is Empty").alert();
            } else {
                sale = new Sale();
                Date ndd = new SimpleDateFormat("dd-MM-yyyy").parse(nextdate.getText());
                java.sql.Date nd = new java.sql.Date(ndd.getTime());
                sale.setBillno(Integer.valueOf(billno.getText()));
                sale.setStartdate(java.sql.Date.valueOf(startdate));
                sale.setShopname(customername.getValue().toString());
                sale.setNextdate(nd);
                if (onekgQty == null) {
                    onekgQty = "0";
                }
                if (twokgQty == null) {
                    twokgQty = "0";
                }
                if (fourkgQty == null) {
                    fourkgQty = "0";
                }
                if (fivekgQty == null) {
                    fivekgQty = "0";
                }
                if (sixkgQty == null) {
                    sixkgQty = "0";
                }
                if (ninekgQty == null) {
                    ninekgQty = "0";
                }
                sale.setOnekgqty(Integer.valueOf(onekgQty));
                sale.setTwokgqty(Integer.valueOf(twokgQty));
                sale.setFourkgqty(Integer.valueOf(fourkgQty));
                sale.setFivekgqty(Integer.valueOf(fivekgQty));
                sale.setSixkgqty(Integer.valueOf(sixkgQty));
                sale.setNinekgqty(Integer.valueOf(ninekgQty));
                if (onekgAmt == null) {
                    onekgAmt = "0.00";
                }
                if (twokgAmt == null) {
                    twokgAmt = "0.00";
                }
                if (fourkgAmt == null) {
                    fourkgAmt = "0.00";
                }
                if (fivekgAmt == null) {
                    fivekgAmt = "0.00";
                }
                if (sixkgAmt == null) {
                    sixkgAmt = "0.00";
                }
                if (ninekgAmt == null) {
                    ninekgAmt = "0.00";
                }
                sale.setOnekgamount(BigDecimal.valueOf(Double.valueOf(onekgAmt)));
                sale.setTwokgamount(BigDecimal.valueOf(Double.valueOf(twokgAmt)));
                sale.setFourkgamount(BigDecimal.valueOf(Double.valueOf(fourkgAmt)));
                sale.setFivekgamount(BigDecimal.valueOf(Double.valueOf(fivekgAmt)));
                sale.setSixkgamount(BigDecimal.valueOf(Double.valueOf(sixkgAmt)));
                sale.setNinekgamount(BigDecimal.valueOf(Double.valueOf(ninekgAmt)));
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

    public static List<String> month() {
        List<String> month = new ArrayList<>();

        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("Octomber");
        month.add("November");
        month.add("December");
        //Collections.sort()
        return month;
    }

    public static List<Integer> year() {
        List<Integer> year = new ArrayList<>();

        year.add(2018);
        year.add(2019);
        year.add(2020);
        year.add(2021);
        year.add(2022);
        year.add(2023);

        return year;
    }
}
