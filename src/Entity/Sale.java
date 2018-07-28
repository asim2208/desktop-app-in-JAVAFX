
package Entity;

import java.util.Date;
import java.math.BigDecimal;

public class Sale {
 //sale attributes
    private int saleid;
    private int billno;
    private java.sql.Date date;
    private String shopname;
    private java.sql.Date nextdate;
    private Integer onekgQty ;
    private Integer threekgQty;
    private Integer fivekgQty;
    private Integer ninekgQty;
    private Integer ci = new Integer("0");
    private BigDecimal onekgAmount;
    private BigDecimal threekgAmount;
    private BigDecimal fivekgAmount;
    private BigDecimal ninekgAmount;
    private BigDecimal amount;
    private BigDecimal cm = new BigDecimal("0.00");
     //basic starters and getters
    /*public int getsaleid(){
        return saleid;
    }
    public void setsaleid(int saleid){
        this.saleid = saleid;
    }*/
    public int getBillno(){
        return billno;
    }
    public void setBillno(int billno){
        this.billno = billno;
    }
    public Date getDate(){
        return date;
    }
    public void setStartdate(java.sql.Date date){
        this.date = date ;
    }
    public String getShopname(){
        return shopname;
    }
    public void setShopname(String shopname){
        this.shopname = shopname;
    }
    public Date getNextdate(){
        return nextdate;
    }
    public void setNextdate(java.sql.Date nextdate){
        this.nextdate = nextdate;
    }
    public Integer getOnekgqty(){
        return onekgQty;
    }
    public void setOnekgqty(Integer onekgQty){
        if (onekgQty.compareTo(ci)==0){
            onekgQty = null;
        }else {
        this.onekgQty = onekgQty;
        }
    }
    public Integer getThreekgqty(){
        return threekgQty;
    }
    public void setThreekgqty(Integer threekgQty){
        if (threekgQty.compareTo(ci)==0){
            threekgQty = null;
        }else{
            this.threekgQty = threekgQty;
        }
    }
    public Integer getFivekgqty(){
        return fivekgQty;
    }
    public void setFivekgqty(Integer fivekgQty){
        if (fivekgQty.compareTo(ci)==0){
            fivekgQty = null;
        }else{
            this.fivekgQty = fivekgQty;
        }
    }
    public Integer getNinekgqty(){
        return ninekgQty;
    }
    public void setNinekgqty(Integer ninekgQty){
        if (ninekgQty.compareTo(ci)==0){
            ninekgQty = null;
        }else {
        this.ninekgQty = ninekgQty;
        }
    }
    public BigDecimal getonekgAmount(){
        return onekgAmount;
    }
    public void setonekgAmount(BigDecimal onekgAmount){
        if ((onekgAmount.compareTo(cm)) == 0){
            this.onekgAmount = null;
        }else {
        this.onekgAmount = onekgAmount;
        }
    }
        
    public BigDecimal getthreekgAmount(){
        return threekgAmount;
    }
    public void setthreekgAmount(BigDecimal threekgAmount){
        if ((threekgAmount.compareTo(cm)) == 0){
            this.threekgAmount = null;
        }else {
        this.threekgAmount = threekgAmount;
        }
    }
    public BigDecimal getfivekgAmount(){
        return fivekgAmount;
    }
    public void setfivekgAmount(BigDecimal fivekgAmount){
        if ((fivekgAmount.compareTo(cm)) == 0){
            this.fivekgAmount = null;
        }else{
        this.fivekgAmount = fivekgAmount;
        }
    }
    public BigDecimal getninekgAmount(){
        return ninekgAmount;
    }
    public void setninekgAmount(BigDecimal ninekgAmount){
        if ((ninekgAmount.compareTo(cm)) == 0){
            this.ninekgAmount = null;
        }else{
        this.ninekgAmount = ninekgAmount;
        }
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    
       
}
