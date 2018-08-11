package firesafetyapp;
import Database.DBConnect;

import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

public class printtoPDF {
    private static DBConnect database;
        
    public void convertToPDF()throws FileNotFoundException, DocumentException {
     Document document = new Document();
        PdfPTable table = new PdfPTable(6);
    
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("shopname");
        table.addCell("contact");
        table.addCell("adress");
        table.addCell("area");
        table.addCell("city");
        table.addCell("zip");
        //table.setHeaderRows(1);
        //PdfPCell[] cells = table.getRow(0).getCells(); 
	  /*for (int j=0;j<cells.length;j++){
	     cells[j].setBackgroundColor(BaseColor.GRAY);
	  }*/
        database = new DBConnect();
        ArrayList<String> list=null;
        list = new ArrayList<>();
        list = DBConnect.getCustomer("anas");
        table.addCell(list.get(0));
        table.addCell(list.get(1));
        table.addCell(list.get(2));
        table.addCell(list.get(3));
        table.addCell(list.get(4));
        table.addCell(list.get(5));
    
          PdfWriter.getInstance(document, new FileOutputStream("sample4.pdf"));
	  document.open();
          document.add(table);
	  document.close();
	  System.out.println("Done");

        }
    
    }
