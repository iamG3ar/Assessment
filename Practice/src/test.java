import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;

public class test {

    /*
       ex. java TextFileToPDF  c:\temp\text.txt  c:\temp\text.pdf
    */
    public static void main (String [] args){
        BufferedReader input = null;
        Document output = null;
        System.out.println("Convert text file to pdf");
        System.out.println("input  : " + args[0]);
        System.out.println("output : " + args[1]);
        try {
            // text file to convert to pdf as args[0]
            input =
                    new BufferedReader (new FileReader(args[0]));
            // letter 8.5x11
            //    see com.lowagie.text.PageSize for a complete list of page-size constants.
            output = new Document(PageSize.LETTER, 40, 40, 40, 40);
            // pdf file as args[1]
            PdfWriter.getInstance(output, new FileOutputStream (args[1]));

            output.open();
            output.addAuthor("RealHowTo");
            output.addSubject(args[0]);
            output.addTitle(args[0]);
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(10);
            Font italic_font = new Font();
            italic_font.setStyle(Font.ITALIC);
            italic_font.setSize(10);

            String line = "";
            while(null != (line = input.readLine())) {
                System.out.println(line);
                Paragraph p = new Paragraph(line, bold_font);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                output.add(p);

            }
            System.out.println("Done.");
            output.close();
            input.close();
            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}