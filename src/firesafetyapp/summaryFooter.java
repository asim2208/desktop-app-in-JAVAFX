package firesafetyapp;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class summaryFooter {
    private FlowPane footer;
    
    private Label onekgqty;
    private Label twokgqty;
    private Label fourkgqty;
    private Label fivekgqty;
    private Label sixkgqty;
    private Label ninekgqty;
    private Label totalamount;
    private Label onekgamount;
    private Label twokgamount;
    private Label fourkgamount;
    private Label fivekgamount;
    private Label sixkgamount;
    private Label ninekgamount;
    
    private TextField[] qtytf = new TextField[6];
    private TextField Qtytf;
    private TextField[] amounttf = new TextField[7];
    private TextField Amounttf;
    
    public FlowPane getsummaryFooter(){
        footer = new FlowPane();
        
        onekgqty = new Label("OneKG Qty:");
        onekgqty.setStyle("footersummary");
        twokgqty = new Label("TwoKG Qty:");
        fourkgqty = new Label("FourKG Qty:");
        fivekgqty = new Label("FiveKG Qty:");
        sixkgqty = new Label("SixKG Qty:");
        ninekgqty = new Label("NineKG Qty:");
        onekgamount = new Label("OneKG Amount:");
        twokgamount = new Label("ThreeKG Amount:");
        fourkgamount = new Label("FourKG Amount:");
        fivekgamount = new Label("FiveKG Amount:");
        sixkgamount = new Label("SixKG Amount:");
        ninekgamount = new Label("NineKG Amount:");
        totalamount = new Label("Total Amount:");
        
        for(int i= 0;i<6;i++){
           Qtytf = qtytf[i] = new TextField("0");
           Qtytf.setMaxWidth(30);
           Qtytf.setEditable(false);
           
        }
        for(int i=0;i<7;i++){
           Amounttf = amounttf[i] =new TextField("0.00");
           Amounttf.setMaxWidth(75);
           Amounttf.setEditable(false);
           
        }
        footer.getChildren().addAll(onekgqty,qtytf[0],onekgamount,amounttf[0],twokgqty,qtytf[1],twokgamount,amounttf[1],fourkgqty,qtytf[2],fourkgamount,amounttf[2],
                                    fivekgqty,qtytf[3],fivekgamount,amounttf[3],sixkgqty,qtytf[4],sixkgamount,amounttf[4],ninekgqty,qtytf[5],ninekgamount,amounttf[5],
                                    totalamount,amounttf[6]);
      //  footer.setId("header");
        footer.getStyleClass().addAll("HBox");
        return footer;
    }
    
    public void setfooter(String oneqty,String oneamount,String twoqty,String twoamount,String fourqty,String fouramount,String fiveqty,String fiveamount,String sixqty,String sixamount,String nineqty,String nineamount, String totalamount){
        
        if(oneqty != null){
            qtytf[0].setText(oneqty);
        }
        if(oneamount != null){
            amounttf[0].setText(oneamount);
        }
        if(twoqty != null){
            qtytf[1].setText(twoqty);
        }
        if(twoamount!=null){
            amounttf[1].setText(twoamount);
        }
        if(fourqty != null){
            qtytf[2].setText(fourqty);
        }
        if(fouramount!=null){
            amounttf[2].setText(fouramount);
        }
        if(fiveqty != null){
            qtytf[3].setText(fiveqty);
        }
        if(fiveamount != null){
            amounttf[3].setText(fiveamount);
        }
        if(sixqty != null){
            qtytf[4].setText(sixqty);
        }
        if(sixamount!=null){
            amounttf[4].setText(sixamount);
        }
        if(nineqty != null){
            qtytf[5].setText(nineqty);
        }
        if(nineamount != null){
            amounttf[5].setText(nineamount);
        }
        amounttf[6].setText(totalamount);
    }
    
    public void reset(){
        for(int i= 0;i<6;i++){
           qtytf[i].setText("0");
           }
        for(int i=0;i<7;i++){
           amounttf[i].setText("0.00");
           }
    }
}
