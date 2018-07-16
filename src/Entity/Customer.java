package Entity;

public class Customer {
    
    //customer attributes
    private int id;
    private String shopname;
    private long shopcont;
    private String streetAdd;
    private String area;
    private String city;
    private int zipcode;
    
    //basic starter and getters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getShopName(){
        return shopname;
    }
    
    public void setShopName(String shopname){
        this.shopname = shopname;
    }
    
    public long getShopCont(){
        return shopcont;
    }
    
    public void setShopCont(long shopcont){
        this.shopcont = shopcont;
    }
    
    public String getStreetAdd(){
        return streetAdd;
    }
    
    public void setStreetAdd(String streetAdd){
        this.streetAdd = streetAdd;
    }
    
    public String getArea(){
        return area;
    }
    
    public void setArea(String area){
        this.area = area;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public int getZipcode(){
        return zipcode;
        
    }
    
    public void setZipcode(int zipcode){
        this.zipcode = zipcode;
    }
    
    
    
    //override of the to string fot customer
    @Override
    public String toString(){
        return "TD = "+ id + ", Shop Name = "+ shopname + ", Shop Contact = "+ shopcont + ", Street Address = "+ streetAdd +", Area = "+ area
                +", city = "+city+", zipcode = "+zipcode;
    }
}
