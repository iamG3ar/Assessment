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
            //
            commandList.add(String.format("%s %d", ));
        }



    }
    public static void convertToPDF() {

    }
}
