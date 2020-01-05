import java.io.*;
import java.awt.*;
import com.itextpdf.text.Document;
//import com.lowagie.text.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.pdf.*;
import java.io.*;
import java.util.zip.*;

    public class test2 {
        public static void main(String arg[]){

            try{
                InputStreamReader in= new InputStreamReader(System.in);
                BufferedReader bin= new BufferedReader(in);
                System.out.println("Enter text:");
                String text=bin.readLine();
                Document document = new Document(PageSize.A4, 36, 72, 108, 180);
                PdfWriter.getInstance(document,new FileOutputStream("pdfFile.pdf"));
                document.open();
                Paragraph p = new Paragraph(text);
                p.setIndentationRight(10 * 2);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(p);
                System.out.println("Text is inserted into pdf file");
                document.close();
            }catch(Exception e){}
        }
    }
