import java.io.*;
import java.util.Scanner;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


public class ToPDF {
    public static Font mainFont;
    public static int indent = 0;
    public static boolean fill = false;
    public static Font normal_font = new Font();
    public static Font bold_font = new Font();
    public static Font italic_font = new Font();
    public static Font large_font = new Font();

    public static void main(String[] args) {
        System.out.println("PDF Converter");
        System.out.println("Enter file path");

        Scanner scanner = new Scanner(System.in);
        String filepath = scanner.nextLine();
        File file = new File(filepath);

        if (file.getName().endsWith(".txt")) {
            if (convertTextToPDF(file)) {
                System.out.println("PDF created");
            }
        }
    }
        public static boolean convertTextToPDF(File file){
            FileInputStream fileStream = null;
            DataInputStream in = null;
            InputStreamReader is = null;
            BufferedReader br = null;

            try {
                //Default settings when opening a PDF doc.
                Document pdfDocument = new Document();
                System.out.println(file.getParent());
                String text_file_name = file.getParent() + "/" + "textToPDF.pdf";
                System.out.println(text_file_name);
                PdfWriter.getInstance(pdfDocument, new FileOutputStream(text_file_name));

                pdfDocument.open();
                pdfDocument.setMarginMirroring(true);
                pdfDocument.setMargins(36, 72, 108, 180);
                pdfDocument.topMargin();

                normal_font.setStyle(Font.NORMAL);
                normal_font.setSize(10);
                mainFont = normal_font;

                bold_font.setStyle(Font.BOLD);
                bold_font.setSize(10);

                italic_font.setStyle(Font.ITALIC);
                italic_font.setSize(10);

                large_font.setStyle(mainFont.getStyle());
                large_font.setSize(20);

                pdfDocument.add(new Paragraph("\n"));

                if (file.exists()) {
                    fileStream = new FileInputStream(file);
                    in = new DataInputStream(fileStream);
                    is = new InputStreamReader(in);
                    br = new BufferedReader(is);

                    //Text file is being read by BufferedReader
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        //Case for all commands
                        String[] stringSplit = strLine.split("\\s+");

                        // the Indent command
                        if (stringSplit[0].toLowerCase().equals(".indent")) {
                            System.out.println("indent with " + stringSplit[1]);
                            int number = Integer.parseInt(stringSplit[1]);
                            indent += number;
                        } else {
                            switch (strLine.toLowerCase()) {
                                case ".paragraph":

                                    System.out.println("paragraph");
                                    Paragraph paragraph = new Paragraph("\n", mainFont);
                                    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                                    pdfDocument.add(paragraph);
                                    break;

                                case ".fill":
                                    System.out.println("fill");
                                    fill = true;
                                    break;

                                case ".nofill":
                                    System.out.println("nofill");
                                    fill = false;
                                    break;

                                case ".regular":
                                    System.out.println("regular");
                                    mainFont = normal_font;
                                    break;

                                case ".italics":
                                    System.out.println("italic");
                                    mainFont = italic_font;
                                    break;

                                case ".bold":
                                    System.out.println("bold");
                                    mainFont = bold_font;
                                    break;

                                case ".large":
                                    System.out.println("large");
                                    mainFont = large_font;
                                    break;

                                default:
                                    //converts the text to a paragraph for proper indentation.
                                    Chunk text = validateFontStyle(strLine);
                                    Phrase textPhrase;
                                    Paragraph paragraph2 = new Paragraph(text);

                                    //Validation if indent is to the left or right.
                                    if (fill == false) {
                                        if (indent < 0) {
                                            paragraph2.setIndentationRight(Math.abs(indent * 10));
                                            pdfDocument.add(paragraph2);
                                        } else if (indent == 0) {
                                            pdfDocument.add(text);
                                        } else {
                                            paragraph2.setIndentationLeft(Math.abs(indent * 10));
                                            pdfDocument.add(paragraph2);
                                        }
                                    } else {
                                        if (indent < 0) {
                                            paragraph2.setIndentationRight(Math.abs(indent * 10));
                                            paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
                                            pdfDocument.add(paragraph2);
                                        } else if (indent == 0) {
                                            paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
                                            pdfDocument.add(text);
                                        } else {
                                            paragraph2.setIndentationLeft(Math.abs(indent * 10));
                                            paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
                                            pdfDocument.add(paragraph2);
                                        }
                                    }

                            }
                        }
                    }
                } else {
                    System.out.println("file does not exist");
                    return false;
                }
                pdfDocument.close();
            } catch (Exception e) {
                System.out.println("FileUtility.convertEmailToPDF(): exception = " + e.getMessage());
            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (fileStream != null) {
                        fileStream.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return true;
        }
        public static Chunk validateFontStyle(String strLine) {
        Chunk text;
            if (mainFont.getStyle() == italic_font.getStyle()) {
                text = new Chunk(" " + strLine + " ", mainFont);
                return text;
            }
            else if (mainFont.getStyle() == bold_font.getStyle()) {
                text = new Chunk(" " + strLine, mainFont);
                return text;
            } else {
                text = new Chunk(strLine, mainFont);
                return text;
            }
        }
}




