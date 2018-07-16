
package firesafetyapp;
import Entity.Customer;
import Entity.Sale;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;


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
        else if(!shopname.getText().matches("[A-Z][a-zA-Z]*") || !shopcontact.getText().matches("\\d{10}")
                || !streetadd.getText().matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)") || !city.getText().matches("([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+)") || !zip.getText().matches("\\d{5}"))
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
    
    //validate sale and ensure completeness
    public static Sale validateSale(TextField billno,LocalDate startdate ,ComboBox customername, TextField nextdate,TextField amount )
    {
       Sale sale = null;
       
       if(billno.getText().isEmpty()|| startdate == null || customername.getValue() == null || nextdate.getText().isEmpty() || amount.getText().isEmpty())
       {
           new PopUp("Error","some Field is empty").alert();
       }
       else
       {
           sale = new Sale();
           
           sale.setBillno(Integer.valueOf(billno.getText()));
           sale.setStartdate(startdate);
           sale.setShopname(customername.getValue().toString());
           sale.setNextdate(nextdate.getText());
           sale.setAmount(Integer.valueOf(amount.getText()));
           
       }
       return sale;
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
