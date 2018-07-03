package Database;

import Entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
            
            String customerStatement = " INSERT INTO CUSTOMERDETAIL (ID,SHOPNAME,SHOPCONTACT, STREETADDRESS, AREA, CITY, ZIPCODE) "
                                        + "VALUES (" + customer.getId()
                                        + ", '" + customer.getShopName()
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
    //to show shopnames in combobox
    public static List<String> storename(){
        List<String>storename = new ArrayList();
        
        try
        {
            getConnection();
            
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CUSTOMERDETAIL");
            while(rs.next()){
               String name = rs.getString("shopname");
               storename.add(name);
            }
       
        }catch (SQLException error){
            error.printStackTrace();
        }catch (ClassNotFoundException error){
            error.printStackTrace();
        }
    return storename;
    }
}
