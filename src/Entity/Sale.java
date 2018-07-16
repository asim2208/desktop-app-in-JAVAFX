
package Entity;
import java.time.LocalDate;

public class Sale {
 //sale attributes
    private int billno;
    private LocalDate date;
    private String shopname;
    private String nextdate;
    private int amount;
     //basic starters and getters 
    public int getBillno(){
        return billno;
    }
    public void setBillno(int billno){
        this.billno = billno;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setStartdate(LocalDate date){
        this.date = date ;
    }
    public String getShopname(){
        return shopname;
    }
    public void setShopname(String shopname){
        this.shopname = shopname;
    }
    public String getNextdate(){
        return nextdate;
    }
    public void setNextdate(String nextdate){
        this.nextdate = nextdate;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    
       
}
