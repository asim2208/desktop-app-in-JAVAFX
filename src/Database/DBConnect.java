package Database;

import Entity.Customer;
import Entity.Sale;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBConnect {
    
    //connection object
    private static Connection conn;
    private static List<String>storename ;
    
    //credential to access server
    private static String url = "jdbc:derby://localhost:1527/firesafetydatabase;create=true";
    private static String user = "root";
    private static String pass = "12345";
    
    public static Connection connect()throws SQLException{
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        }catch(ClassNotFoundException cnfe){
            
            System.err.println("Error: "+cnfe.getMessage());
           
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }
    
        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    //returns connection if server is up and running
    public static Connection getConnection()throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
    
    //to enter new customer details in table
    public void enterCustomer(Customer customer){
        
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
            
            String customerStatement = " INSERT INTO CUSTOMERDETAILS (SHOPNAME,SHOPCONTACT, ADDRESS, AREA, CITY, ZIPCODE) "
                                        + "VALUES ('"
                                        + customer.getShopName()
                                        + "', " + customer.getShopCont()
                                        + ", '" + customer.getStreetAdd()
                                        + "', '" + customer.getArea()
                                        + "', '" + customer.getCity()
                                        +"', " + customer.getZipcode()
                                        +")";
            
            s.execute(customerStatement);
           
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    //to get customer details for edit or delete purpose
    public static ArrayList<String> getCustomer(String customerName){
        ArrayList<String> list = new ArrayList<>(); //name = null,contact = null,address = null,area = null,city = null,zip = null;
            
        try{
            getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM CUSTOMERDETAILS WHERE SHOPNAME ='"+customerName+"'");
                while(rs.next()){
                    list.add(rs.getString("shopname"));
                    
                    list.add(rs.getString("shopcontact"));
                    list.add(rs.getString("address"));
                    list.add(rs.getString("area"));
                    list.add(rs.getString("city"));
                    list.add(rs.getString("zipcode"));
                }
            }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
       return list;
    }
    
    // to show customer details in table
    public ObservableList<Customer> getCustomerList(){
        ObservableList<Customer> data;
        data = FXCollections.observableArrayList();
        try{
            getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM CUSTOMERDETAILS");
                while (rs.next()) {
                    Customer cl = new Customer();
                    cl.setShopName(rs.getString("shopname"));
                    cl.setShopCont(Long.parseLong(rs.getString("shopcontact")));
                    cl.setStreetAdd(rs.getString("address"));
                    cl.setArea(rs.getString("area"));
                    cl.setCity(rs.getString("city"));
                    cl.setZipcode(Integer.valueOf(rs.getString("zipcode")));
                    data.add(cl);
                    System.out.println("data "+rs.getString("shopname"));
                
                //table.getColumns().addAll(shopName,shopContact,shopAddress,area,city,zipcode);
                //table.setItems(data);
            }
            }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return data;
    }
    
    //update customer details
    public void updateCustomer(Customer customer,String customerName){
        try{
            int id = Integer.valueOf(getCustomerid(customerName));
            getConnection();
            
            Statement s = conn.createStatement();
                String updateCustomerstatement = "UPDATE CUSTOMERDETAILS "
                        +"SET SHOPNAME = '"+customer.getShopName()
                        +"', SHOPCONTACT = "+customer.getShopCont()
                        +", ADDRESS = '"+customer.getArea()
                        +"', AREA = '"+customer.getArea()
                        +"', CITY = '"+customer.getCity()
                        +"', ZIPCODE = "+customer.getZipcode()
                        +"WHERE CUSTOMERID = "+id;
                
                s.execute(updateCustomerstatement);
            
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    //delete customer details
    public void deleteCustomer(String customerName){
        try{
            int id = Integer.valueOf(getCustomerid(customerName));
            getConnection();
            
            Statement s = conn.createStatement();
                String updateCustomerstatement = "DELETE FROM CUSTOMERDETAILS WHERE CUSTOMERID = "+id;
                
                s.execute(updateCustomerstatement);
            
        }catch (SQLException error){
            error.printStackTrace();
            
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    
    
    //to insert sale details
    public void enterSale(Sale sale){
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
                String saleStatement = " INSERT INTO SALEDETAILS (BILL_NO,CUSTOMER_ID,RENEW_DATE,NEXTRENEW_DATE,ONEKG_QTY,ONEKG_AMOUNT,"
                        + "TWOKG_QTY,TWOKG_AMOUNT,FOURKG_QTY,FOURKG_AMOUNT,FIVEKG_QTY,FIVEKG_AMOUNT,SIXKG_QTY,SIXKG_AMOUNT,"
                        + "NINEKG_QTY,NINEKG_AMOUNT, TOTAL_AMOUNT)"
                        +"VALUES ("+sale.getBillno()
                        +", "+Integer.valueOf(getCustomerid(sale.getShopname()))
                        +", '"+sale.getDate()
                        +"', '"+sale.getNextdate()
                        +"', "+sale.getOnekgqty()
                        +", "+sale.getOnekgamount()
                        +", "+sale.getTwokgqty()
                        +", "+sale.getTwokgamount()
                        +", "+sale.getFourkgqty()
                        +", "+sale.getFourkgamount()
                        +", "+sale.getFivekgqty()
                        +", "+sale.getFivekgamount()
                        +", "+sale.getSixkgqty()
                        +", "+sale.getSixkgamount()
                        +", "+sale.getNinekgqty()
                        +", "+sale.getNinekgamount()
                        +", "+sale.getAmount()
                        +" )";
                s.execute(saleStatement);
            
        }catch(SQLException error){
            error.printStackTrace();
        }catch(ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    //to show sale details in table 
    public ObservableList<Sale> getSaleList(){
        
        ObservableList<Sale> data;
        data = FXCollections.observableArrayList();
        try{
            getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM SALEDETAILS");
                while(rs.next()){
                    Sale cl = new Sale();
                    cl.setBillno(Integer.valueOf(rs.getString("bill_no")));
                    cl.setStartdate(rs.getDate("renew_date"));
                    cl.setShopname(getCustomerName(rs.getInt("customer_id")));
                    cl.setNextdate(rs.getDate("nextrenew_date"));
                    cl.setOnekgqty(rs.getInt("onekg_qty"));
                    cl.setOnekgamount(BigDecimal.valueOf(rs.getDouble("onekg_amount")));
                    cl.setTwokgqty(rs.getInt("twokg_qty"));
                    cl.setTwokgamount(BigDecimal.valueOf(rs.getDouble("twokg_amount")));
                    cl.setFourkgqty(rs.getInt("fourkg_qty"));
                    cl.setFourkgamount(BigDecimal.valueOf(rs.getDouble("fourkg_amount")));
                    cl.setFivekgqty(rs.getInt("fivekg_qty"));
                    cl.setFivekgamount(BigDecimal.valueOf(rs.getDouble("fivekg_amount")));
                    cl.setSixkgqty(rs.getInt("sixkg_qty"));
                    cl.setSixkgamount(BigDecimal.valueOf(rs.getDouble("sixkg_amount")));
                    cl.setNinekgqty(rs.getInt("ninekg_qty"));
                    cl.setNinekgamount(BigDecimal.valueOf(rs.getDouble("ninekg_amount")));
                    cl.setAmount(BigDecimal.valueOf(rs.getDouble("total_amount")));
                    data.add(cl);
                    //System.out.println("data "+rs.getString("threekg_amount"));
                }
            
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return data;
    }
    public ObservableList<Sale> getReport(String query){
        
        ObservableList<Sale> data;
        //month=month+1;
        data = FXCollections.observableArrayList();
        try{
            getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM SALEDETAILS WHERE "+query);
                while(rs.next()){
                    Sale cl = new Sale();
                    cl.setBillno(Integer.valueOf(rs.getString("bill_no")));
                    cl.setStartdate(rs.getDate("renew_date"));
                    cl.setShopname(getCustomerName(rs.getInt("customer_id")));
                    cl.setNextdate(rs.getDate("nextrenew_date"));
                    cl.setOnekgqty(rs.getInt("onekg_qty"));
                    cl.setOnekgamount(BigDecimal.valueOf(rs.getDouble("onekg_amount")));
                    cl.setTwokgqty(rs.getInt("twokg_qty"));
                    cl.setTwokgamount(BigDecimal.valueOf(rs.getDouble("twokg_amount")));
                    cl.setFourkgqty(rs.getInt("fourkg_qty"));
                    cl.setFourkgamount(BigDecimal.valueOf(rs.getDouble("fourkg_amount")));
                    cl.setFivekgqty(rs.getInt("fivekg_qty"));
                    cl.setFivekgamount(BigDecimal.valueOf(rs.getDouble("fivekg_amount")));
                    cl.setSixkgqty(rs.getInt("sixkg_qty"));
                    cl.setSixkgamount(BigDecimal.valueOf(rs.getDouble("sixkg_amount")));
                    cl.setNinekgqty(rs.getInt("ninekg_qty"));
                    cl.setNinekgamount(BigDecimal.valueOf(rs.getDouble("ninekg_amount")));
                    cl.setAmount(BigDecimal.valueOf(rs.getDouble("total_amount")));
                    //cl.setTotal(BigDecimal.valueOf(rs.getDouble("TOTAL")));
                    data.add(cl);
                    //System.out.println("data "+rs.getString("threekg_amount"));
                }
            
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return data;
    }
    
    //report by month
    public ArrayList<String> getTotal(String query){
        ArrayList<String> result = null;
        result = new ArrayList<>();
        try{
        getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT SUM(ONEKG_QTY)AS ONEKGQTY,SUM(ONEKG_AMOUNT) AS ONEKGAMOUNT,SUM(TWOKG_QTY)AS TWOKGQTY,"
                            + "SUM(TWOKG_AMOUNT)AS TWOKGAMOUNT,SUM(FOURKG_QTY) AS FOURKGQTY, SUM(FOURKG_AMOUNT) AS FOURKGAMOUNT,"
                            + "SUM(FIVEKG_QTY)AS FIVEKGQTY,SUM(FIVEKG_AMOUNT)AS FIVEKGAMOUNT,"
                            + "SUM(SIXKG_QTY) AS SIXKGQTY, SUM(SIXKG_AMOUNT)AS SIXKGAMOUNT,SUM(NINEKG_QTY) AS NINEKGQTY,"
                            + "SUM(NINEKG_AMOUNT)AS NINEKGAMOUNT, SUM(TOTAL_AMOUNT) AS TOTAL FROM SALEDETAILS WHERE "+query);
                while(rs.next()){
                        result.add(rs.getString("onekgqty"));
                        result.add(rs.getString("onekgamount"));
                        result.add(rs.getString("twokgqty"));
                        result.add(rs.getString("twokgamount"));
                        result.add(rs.getString("fourkgqty"));
                        result.add(rs.getString("fourkgamount"));
                        result.add(rs.getString("fivekgqty"));
                        result.add(rs.getString("fivekgamount"));
                        result.add(rs.getString("sixkgqty"));
                        result.add(rs.getString("sixkgamount"));
                        result.add(rs.getString("ninekgqty"));
                        result.add(rs.getString("ninekgamount"));
                        result.add(rs.getString("total"));
                    }
            
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        
        return result;
    }
    
    public ObservableList<Sale> getDailyreport(String fdate, String ndate){
        ObservableList<Sale> data;
        //month=month+1;
        data = FXCollections.observableArrayList();
        try{
            getConnection();
            Statement s = conn.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM SALEDETAILS WHERE RENEW_DATE BETWEEN '"+fdate+"' AND '"+ndate+"'");
                while(rs.next()){
                    Sale cl = new Sale();
                    cl.setBillno(Integer.valueOf(rs.getString("bill_no")));
                    cl.setStartdate(rs.getDate("renew_date"));
                    cl.setShopname(getCustomerName(rs.getInt("customer_id")));
                    cl.setNextdate(rs.getDate("nextrenew_date"));
                    cl.setOnekgqty(rs.getInt("onekg_qty"));
                    cl.setOnekgamount(BigDecimal.valueOf(rs.getDouble("onekg_amount")));
                    cl.setTwokgqty(rs.getInt("twokg_qty"));
                    cl.setTwokgamount(BigDecimal.valueOf(rs.getDouble("twokg_amount")));
                    cl.setFourkgqty(rs.getInt("fourkg_qty"));
                    cl.setFourkgamount(BigDecimal.valueOf(rs.getDouble("fourkg_amount")));
                    cl.setFivekgqty(rs.getInt("fivekg_qty"));
                    cl.setFivekgamount(BigDecimal.valueOf(rs.getDouble("fivekg_amount")));
                    cl.setSixkgqty(rs.getInt("sixkg_qty"));
                    cl.setSixkgamount(BigDecimal.valueOf(rs.getDouble("sixkg_amount")));
                    cl.setNinekgqty(rs.getInt("ninekg_qty"));
                    cl.setNinekgamount(BigDecimal.valueOf(rs.getDouble("ninekg_amount")));
                    cl.setAmount(BigDecimal.valueOf(rs.getDouble("total_amount")));
                    //cl.setTotal(BigDecimal.valueOf(rs.getDouble("TOTAL")));
                    data.add(cl);
                    //System.out.println("data "+rs.getString("threekg_amount"));
                }
            
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return data;
    }
    //to show customer names in combobox
    public static List<String> storename(){
        storename = new ArrayList();
        
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CUSTOMERDETAILS");
            while(rs.next()){
               String name = rs.getString("shopname");
               storename.add(name);
            }
       
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
        Collections.sort(storename);
        
    return storename;
    }
    
    //to get customerid from customer name 
    public String getCustomerid(String string){
        
        String id = null;
        try{
            getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT CUSTOMERID FROM CUSTOMERDETAILS WHERE SHOPNAME = '"+string+"'");
            while(rs.next()){
                id = rs.getString("customerid");
               
            }
       
        }catch(SQLException error){
                    error.printStackTrace();
                }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return id;
    }
    
    //to get customer name from id
    public String getCustomerName(int id){
    String name = null;
    try{
        getConnection();
        
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT SHOPNAME FROM CUSTOMERDETAILS WHERE CUSTOMERID = "+id);
        while(rs.next()){
            name = rs.getString("shopname");
        }
    }catch (SQLException error){
        error.printStackTrace();
    }catch (ClassNotFoundException error){
        error.printStackTrace();
    }
   return name; 
}
    
}
