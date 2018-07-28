package Database;

import Entity.Customer;
import Entity.Sale;
import firesafetyapp.PopUp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBConnect {
    
    //connection object
    private static Connection conn;
    
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
            conn.close();
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    }
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
            conn.close();
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
       return list;
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
            
            conn.close();
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
            
            conn.close();
        }catch (SQLException error){
            error.printStackTrace();
            
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    
    
    
    public void enterSale(Sale sale){
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
            
            String saleStatement = " INSERT INTO SALEDETAILS (BILL_NO,CUSTOMER_ID,START_DATE,NEXT_DATE,ONEKG_QTY,THREEKG_QTY,FIVEKG_QTY,NINEKG_QTY,ONEKG_AMOUNT,THREEKG_AMOUNT,FIVEKG_AMOUNT,NINEKG_AMOUNT, TOTAL_AMOUNT)"
                                    +"VALUES ("+sale.getBillno()
                                    +", "+Integer.valueOf(getCustomerid(sale.getShopname()))
                                    +", '"+sale.getDate()
                                    +"', '"+sale.getNextdate()
                                    +"', "+sale.getOnekgqty()
                                    +", "+sale.getThreekgqty()
                                    +", "+sale.getFivekgqty()
                                    +", "+sale.getNinekgqty()
                                    +", "+sale.getonekgAmount()
                                    +", "+sale.getthreekgAmount()
                                    +", "+sale.getfivekgAmount()
                                    +", "+sale.getninekgAmount()
                                    +", "+sale.getAmount()
                                    +" )";
            s.execute(saleStatement);
    
            conn.close();
        }catch(SQLException error){
            error.printStackTrace();
        }catch(ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    
    //to show shopnames in combobox
    public static List<String> storename(){
        List<String>storename = new ArrayList();
        
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CUSTOMERDETAILS");
            while(rs.next()){
               String name = rs.getString("shopname");
               storename.add(name);
            }
       
            conn.close();
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    return storename;
    }
    public String getCustomerid(String string){
        
        String id = null;
        try{
            getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT CUSTOMERID FROM CUSTOMERDETAILS WHERE SHOPNAME = '"+string+"'");
            while(rs.next()){
                id = rs.getString("customerid");
               
            }
            conn.close();
        }catch(SQLException error){
                    error.printStackTrace();
                }
        catch(ClassNotFoundException error){
            error.printStackTrace();
        }
        
        return id;
    }
}
