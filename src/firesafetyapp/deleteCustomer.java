package firesafetyapp;

import Database.DBConnect;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class deleteCustomer {

    DBConnect database = new DBConnect();
    String customerName = null;

    private heading headingobj = new heading();
    String string = "Delete customer Details :";

    private ComboBox<String> selectCustomer = new ComboBox<>();
    private Label selectCustomerlabel = new Label("Select Customer :");
    private Button editButton = new Button("Search");

    private Label shopNamelabel = new Label("Shop Name :");
    private TextField shopNametf = new TextField();

    private Label shopContactlabel = new Label("Contact Number :");
    private TextField shopContacttf = new TextField();

    private Label streetAddlabel = new Label("Street :");
    private TextField streetAddtf = new TextField();

    private Label areaLabel = new Label("area");
    private TextField areaTf = new TextField();

    private Label cityLabel = new Label("City");
    private TextField cityTf = new TextField();

    private Label zipLabel = new Label("ZipCode");
    private TextField zipTf = new TextField();
    private Button deleteButton = new Button("Delete");

    public deleteCustomer() {
        reset();
        selectCustomer.getItems().addAll(DBConnect.storename());

    }

    public GridPane addGridpane() {

        shopNametf.setEditable(false);
        shopContacttf.setEditable(false);
        streetAddtf.setEditable(false);
        areaTf.setEditable(false);
        cityTf.setEditable(false);
        zipTf.setEditable(false);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.getStyleClass().addAll("background");
        gridPane.setVgap(10);
        gridPane.setHgap(30);

        int gridsize = 6;
        for (int i = 0; i <= gridsize; i++) {
            RowConstraints rowCon = new RowConstraints();
            //rowCon.setPercentHeight(gridsize);
            gridPane.getRowConstraints().add(rowCon);
        }

        gridPane.add(headingobj.hBox(string), 0, 0, 2, 1);
        gridPane.add(selectCustomerlabel, 0, 1);
        gridPane.add(selectCustomer, 1, 1);
        gridPane.add(editButton, 2, 1);
        gridPane.add(shopNamelabel, 0, 2);
        gridPane.add(shopContactlabel, 3, 2);
        gridPane.add(shopNametf, 0, 3, 2, 1);
        gridPane.add(shopContacttf, 3, 3, 2, 1);
        gridPane.add(streetAddlabel, 0, 4);
        gridPane.add(streetAddtf, 0, 5, 2, 1);
        gridPane.add(areaLabel, 0, 6);
        gridPane.add(cityLabel, 1, 6);
        gridPane.add(zipLabel, 2, 6);
        gridPane.add(areaTf, 0, 7);
        gridPane.add(cityTf, 1, 7);
        gridPane.add(zipTf, 2, 7);
        gridPane.add(deleteButton, 0, 9);

        editButton.setOnAction(e -> {

            customerName = selectCustomer.getValue();

            deleteCustomer(customerName);
        });
        deleteButton.setOnAction(e -> {

            database.deleteCustomer(customerName);
            new PopUp("Deleted", "customer data deleted successfully").alert();
            reset();
        });

        return gridPane;
    }

    private ArrayList<String> deleteCustomer(String customername) {
        //System.out.println(customerName);

        ArrayList<String> list = null;
        list = DBConnect.getCustomer(customername);

        if (customername.equals(list.get(0))) {
            shopNametf.setText(list.get(0));
            shopContacttf.setText(list.get(1));
            streetAddtf.setText(list.get(2));
            areaTf.setText(list.get(3));
            cityTf.setText(list.get(4));
            zipTf.setText(list.get(5));
        }
        return list;
    }

    public void reset() {
        //selectCustomer.setValue(null);
        shopNametf.clear();
        shopContacttf.clear();
        streetAddtf.clear();
        areaTf.clear();
        cityTf.clear();
        zipTf.clear();
    }

}
