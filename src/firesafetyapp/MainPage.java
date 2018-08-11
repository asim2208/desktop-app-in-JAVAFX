package firesafetyapp;

import Database.DBConnect;
import static firesafetyapp.Handler.getWindowsize;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainPage extends Application {

    Stage Window;

    DBConnect database = new DBConnect();
    //editCustomer editCustomerobj = null;
    private editCustomer editCustomerobj = null;
    private printtoPDF pdfobj = new printtoPDF();

    private CustomerDetail customerdetailform = new CustomerDetail();
    private SaleDetail saledetailform = null;
    private deleteCustomer deleteCustomerobj = null;
    private customerSummary customerSummaryobj = new customerSummary();
    private saleSummary saleSummaryobj = new saleSummary();
    private monthlyReport monthReportobj = new monthlyReport();
    private dailyReport dailyReportobj = new dailyReport();

    //basic gui items
    private MenuButton Customerdetailprompt = new MenuButton("Customer Details");
    private MenuButton SaleEntryprompt = new MenuButton("Sale Entry");
    private MenuButton summary = new MenuButton("Summary");
    private MenuButton report = new MenuButton("Report");
    private MenuButton pdf = new MenuButton("print");

    private MenuItem addCustomermenu = new MenuItem("Add");
    private MenuItem editCustomermenu = new MenuItem("Edit");
    private MenuItem deleteCustomermenu = new MenuItem("Delete");

    private MenuItem addSale = new MenuItem("Add");
    private MenuItem deleteSale = new MenuItem("Delete");

    private MenuItem customerSummarymenu = new MenuItem("customer");
    private MenuItem saleSummarymenu = new MenuItem("Sale");

    private MenuItem monthreport = new MenuItem("by Month");
    private MenuItem dailyreport = new MenuItem("by Date");

    private MenuItem printdoc = new MenuItem("printdoc");

    @Override
    public void start(Stage primaryStage) {

        Customerdetailprompt.setPopupSide(Side.RIGHT);
        Customerdetailprompt.getItems().addAll(addCustomermenu, editCustomermenu, deleteCustomermenu);
        Customerdetailprompt.setPrefWidth(200);

        SaleEntryprompt.setPopupSide(Side.RIGHT);
        SaleEntryprompt.getItems().addAll(addSale, deleteSale);
        SaleEntryprompt.setPrefWidth(200);

        summary.setPopupSide(Side.RIGHT);
        summary.getItems().addAll(customerSummarymenu, saleSummarymenu);
        summary.setPrefWidth(200);

        report.setPopupSide(Side.RIGHT);
        report.getItems().addAll(monthreport, dailyreport);
        report.setPrefWidth(200);

        pdf.setPopupSide(Side.RIGHT);
        pdf.getItems().addAll(printdoc);
        pdf.setPrefWidth(200);

        VBox vBox = new VBox(10);

        vBox.getChildren().addAll(Customerdetailprompt, SaleEntryprompt, summary, report, pdf);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.getStyleClass().addAll("background");

        Window = primaryStage;
        BorderPane borderpane = new BorderPane();

        borderpane.setLeft(vBox);
        borderpane.setTop(addHbox());
        Scene scene = new Scene(borderpane, getWindowsize().getWidth() - 100, getWindowsize().getHeight() - 100);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        //poper closing for this class
        primaryStage.setOnCloseRequest(e -> {

            e.consume();
            closeProgram();
        });

        //option for enter new customer
        addCustomermenu.setOnAction(event -> {
            try {
                borderpane.setCenter(customerdetailform.addCustomerdetail());
                //reset();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        });
        editCustomermenu.setOnAction(event -> {
            try {
                editCustomerobj = new editCustomer();
                borderpane.setCenter(editCustomerobj.addGridpane());
                //reset();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }
        });
        deleteCustomermenu.setOnAction(event -> {
            try {
                deleteCustomerobj = new deleteCustomer();
                borderpane.setCenter(deleteCustomerobj.addGridpane());
                //reset();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }
        });

        //option for enter new sale entry
        addSale.setOnAction(event -> {
            try {
                saledetailform = new SaleDetail();
                borderpane.setCenter(saledetailform.addSaledetail());
                // reset();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        customerSummarymenu.setOnAction(event -> {
            try {
                borderpane.setCenter(customerSummaryobj.getCustomerTable());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        saleSummarymenu.setOnAction(event -> {
            try {
                borderpane.setCenter(saleSummaryobj.getSalesummary());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        monthreport.setOnAction(event -> {
            try {
                borderpane.setCenter(monthReportobj.getmonthlyreport());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        dailyreport.setOnAction(event -> {
            try {
                borderpane.setCenter(dailyReportobj.getdailyreport());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        printdoc.setOnAction(event -> {
            try {

                pdfobj.convertToPDF();
                //System.out.println("succ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        primaryStage.setTitle("Choice Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public HBox addHbox() {
        HBox hbox = new HBox();
        hbox.setPrefHeight(75);
        //hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getStyleClass().addAll("HBox");

        Text title = new Text("SAFE FIRE SERVICE");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        title.setSmooth(true);
        title.setFill(Color.WHITE);
        //title.getStyleClass().addAll("header");
        hbox.getChildren().addAll(title);
        return hbox;
    }

    private void closeProgram() {

        ConfirmBox cb = new ConfirmBox();

        boolean answer = cb.alert();

        if (answer) {
            Window.close();
        }
    }

    private void reset() {
        customerdetailform.reset();
        editCustomerobj.reset();
        deleteCustomerobj.reset();
        saledetailform.reset();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
