package firesafetyapp;

import Database.DBConnect;
import Entity.Sale;
import java.math.BigDecimal;
import javafx.geometry.Insets;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
public class saleSummary {
    
    Sale sale = new Sale();
    
    DBConnect database = new DBConnect();
    StackPane root =null;
    TableView<Sale> table = null;
    TableColumn billno =null;
    TableColumn saledate =null;
    TableColumn customername = null;
    TableColumn nextdate = null;
    TableColumn onekgqty = null;
    TableColumn twokgqty = null;
    TableColumn fourkgqty = null;
    TableColumn fivekgqty = null;
    TableColumn sixkgqty = null;
    TableColumn ninekgqty = null;
    TableColumn totalamount = null;
    TableColumn[] Qty = null;
    TableColumn[] Amount = null;
    
    public StackPane getSalesummary(){
        
        table= new TableView<>();
        billno = new TableColumn("Bill No");
    saledate = new TableColumn("Renew Date");
    customername = new TableColumn("Customer Name");
    nextdate = new TableColumn("Next Renew Date");
    onekgqty = new TableColumn("1KG");
    twokgqty = new TableColumn("2KG");
    fourkgqty = new TableColumn("4KG");
    fivekgqty = new TableColumn("5KG");
    sixkgqty = new TableColumn("6KG");
    ninekgqty = new TableColumn("9KG");
    totalamount = new TableColumn("Total Amount");
    Qty = new TableColumn[6];
    Amount = new TableColumn[6];
        for(int i= 0; i<= 5; i++){
            TableColumn qty =Qty[i]= new TableColumn("Qty");
            TableColumn amount = Amount[i]= new TableColumn("Amount");
        }
        onekgqty.getColumns().addAll(Qty[0],Amount[0]);
        twokgqty.getColumns().addAll(Qty[1],Amount[1]);
        fourkgqty.getColumns().addAll(Qty[2],Amount[2]);
        fivekgqty.getColumns().addAll(Qty[3],Amount[3]);
        sixkgqty.getColumns().addAll(Qty[4],Amount[4]);
        ninekgqty.getColumns().addAll(Qty[5],Amount[5]);
        table.getColumns().addAll(billno,saledate,customername,nextdate,onekgqty,twokgqty,fourkgqty,fivekgqty,sixkgqty,ninekgqty,totalamount);
     
        root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().addAll(table);
        
    
        billno.setCellValueFactory(new PropertyValueFactory<>("Billno"));
        saledate.setCellValueFactory(new PropertyValueFactory<>("date"));
        customername.setCellValueFactory(new PropertyValueFactory<>("shopname"));
        nextdate.setCellValueFactory(new PropertyValueFactory<>("Nextdate"));
        Qty[0].setCellValueFactory(new PropertyValueFactory<>("onekgqty"));
        Amount[0].setCellValueFactory(new PropertyValueFactory<>("onekgamount"));
        Qty[1].setCellValueFactory(new PropertyValueFactory<>("twokgqty"));
        Amount[1].setCellValueFactory(new PropertyValueFactory<>("twokgamount"));
        Qty[2].setCellValueFactory(new PropertyValueFactory<>("fourkgqty"));
        Amount[2].setCellValueFactory(new PropertyValueFactory<>("fourkgamount"));
        Qty[3].setCellValueFactory(new PropertyValueFactory<>("fivekgqty"));
        Amount[3].setCellValueFactory(new PropertyValueFactory<>("fivekgamount"));
        Qty[4].setCellValueFactory(new PropertyValueFactory<>("sixkgqty"));
        Amount[4].setCellValueFactory(new PropertyValueFactory<>("sixkgamount"));
        Qty[5].setCellValueFactory(new PropertyValueFactory<>("ninekgqty"));
        Amount[5].setCellValueFactory(new PropertyValueFactory<>("ninekgamount"));
        totalamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        //System.out.println(Qty[0].getCellObservableValue(1));
        table.setItems(database.getSaleList());
    return root;
    } 
}
