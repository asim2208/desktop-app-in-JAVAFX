package firesafetyapp;

import Database.DBConnect;
import Entity.Sale;
import static firesafetyapp.Handler.*;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class monthlyReport {
    
    private Sale sale;
    private DBConnect database;
    private TableView<Sale> table;
    private VBox root;
    private VBox vboxtop;
    private HBox hboxtop;
    private summaryFooter footer;
    String m,year;
    int month;
    private heading headingobj;
    
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
    ArrayList<String> list = null;
    
    
    private Label selectmonthlabel = new Label("Select Month: ");
    private Label selectyearlabel = new Label("Select Year: ");
    
    private ComboBox monthcombobox = new ComboBox();
    private ComboBox yearcombobox = new ComboBox();
    
    private Label onekgqtylabel = new Label("OneKg Qty:");
    private Label totalonekgqty = new Label("0");
    private Label onekgamountlabel = new Label("OneKG Amount :");
    private Label totalonekgamount = new Label("0.00");
    private Label twokgqtylabel = new Label("TwoKG Qty :");
    private Label totaltwokgqty = new Label("0");
    private Label threekgamountlabel = new Label ("TwoKg Amount:");
    private Label totalthreekgamount = new Label("0.00");
    private Label totalsalelabel = new Label("Total Sale :");
    private Label totalsale = new Label();
    private Button getreport = new Button("get report");
            
    public VBox getmonthlyreport(){
        sale = new Sale();
        database = new DBConnect();
        root = new VBox(5);
        vboxtop = new VBox();
        hboxtop = new HBox(5);
        footer = new summaryFooter();
        table = new TableView<>();
        headingobj = new heading();
        
        monthcombobox.getItems().addAll(month());
        yearcombobox.getItems().addAll(year());
        hboxtop.getChildren().addAll(selectmonthlabel,monthcombobox,selectyearlabel,yearcombobox,getreport);
        hboxtop.getStyleClass().addAll("background");
        hboxtop.setPadding(new Insets(10));
       
       vboxtop.getChildren().addAll(headingobj.hBox("Monthly Report :"),hboxtop);
       //vboxtop.setPadding(new Insets(10));
        //hboxbottom.setPadding(new Insets(10));
        //hboxbottom.getChildren().addAll(onekgqtylabel,totalonekgqty,onekgamountlabel,totalonekgamount,threekgqtylabel,totalthreekgqty,threekgamountlabel,totalthreekgamount,totalsalelabel,totalsale);
        
        billno = new TableColumn("Bill No");
    saledate = new TableColumn("Renew Date");
    customername = new TableColumn("Customer Name");
    nextdate = new TableColumn("Next Renew Date");
    onekgqty = new TableColumn("1KG");
    twokgqty = new TableColumn("3KG");
    fourkgqty = new TableColumn("4KG");
    fivekgqty = new TableColumn("5KG");
    sixkgqty = new TableColumn("6KG");
    ninekgqty = new TableColumn("9KG");
    totalamount = new TableColumn("Total Amount");
    Qty = new TableColumn[6];
    Amount = new TableColumn[6];
        for(int i= 0; i<6; i++){
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
       
        root.getChildren().addAll(vboxtop,table,footer.getsummaryFooter());
        root.getStyleClass().addAll("background");
        
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
        
        getreport.setOnAction(e ->{
            month = monthcombobox.getSelectionModel().getSelectedIndex();
            ++month;
            m = String.valueOf(month);
            year = yearcombobox.getValue().toString();
            String query = "MONTH(RENEW_DATE)="+month+" AND YEAR(RENEW_DATE)="+year;
            System.out.println(query);
            list = database.getTotal(query);
            table.setItems(database.getReport(query));
            if(list != null){
            footer.setfooter(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),list.get(5),list.get(6),list.get(7),list.get(8),list.get(9),list.get(10),list.get(11),list.get(12));
            }
            else
            {
                footer.reset();
            }
        });
        
        return root;
    }
    
    
    
}
