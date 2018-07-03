
package firesafetyapp;
import Entity.Customer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Handler {
    
    //validate agent and ensure completeness
    public static Customer validateCustomer(TextField shopname, TextField shopcontact, TextField streetadd, ComboBox area, TextField city, TextField zip)
    {
        Customer customer = null;
        
        if (shopname.getText().isEmpty() || shopcontact.getText().isEmpty() || streetadd.getText().isEmpty() 
                || city.getText().isEmpty() 
                        || zip.getText().isEmpty() || area.getValue() == null)
        {
            new PopUp("Name Error","Name or adress field is empty").alert();
        }
        else if(/*!shopname.getText().matches("[a-zA-Z]+") ||*/ !shopcontact.getText().matches("\\d{10}")
                || /*!streetadd.getText().matches("\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+") || !city.getText().matches("[a-zA-Z]+") ||*/ !zip.getText().matches("\\d{6}"))
        {
            new PopUp("name error", "field not match").alert();
        }
        else
        {
            customer = new Customer();
            
            customer.setId(new SecureRandom().nextInt(99999999) + 100000000);
            customer.setShopName(shopname.getText());
            customer.setShopCont(Long.valueOf(shopcontact.getText()));
            customer.setStreetAdd(streetadd.getText());
            customer.setArea(area.getValue().toString());
            customer.setCity(city.getText());
            customer.setZipcode(Integer.valueOf(zip.getText()));
        }
        
        return customer;
    }
    //method to properly close the program
    public static void closeProgram(Stage window){
        
        ConfirmBox cb = new ConfirmBox();
        
        boolean answer = cb.alert();
        
        if(answer){
            
            //*************************
            //do a routine closing process
            //*************************

            
            window.close();
        }
    }
    public static List<String> area(){
        List<String> area = new ArrayList<>();
        
        area.add("vikhroli");
        area.add("ghatkopar");
        
        Collections.sort(area);
        return area;
        
    }
}
