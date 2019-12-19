import java.io.File;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class ToPDF {
    static Enumeration<String> commands;
    static Vector<String> commandList = new Vector<>();


    public static void main(String[] args) {
        //Using try with exceptions for efficiency.
        try (Scanner scanner = new Scanner(System.in)) {
            String filepath = scanner.nextLine();
            File pdfFile = new File(filepath);

            commandList.add(".paragraph");
            commandList.add(".fill");
            commandList.add(".nofill");
            commandList.add(".regular");
            commandList.add(".italic");
            commandList.add(".bold");
            commandList.add(String.format("%s %d"));
            /* we have commnd .indent 30
            if after the first char which is a '.'
            check if string[1] = 'i';
            if it is then check if string[4] = 't';
            if it is then you know this is an indent command
            meaning after a space " " we can get the number by 
            which to indent by.
            */
            
        }



    }
    public static void convertToPDF() {

    }
}
