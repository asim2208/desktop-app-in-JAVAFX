package firesafetyapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

public class DateConverter {
    //Converter
    public static String setdate(LocalDate localdate){
        
        LocalDate nextyear = localdate.plusYears(1);
        String date = nextyear.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
       return date; 
        
}
}