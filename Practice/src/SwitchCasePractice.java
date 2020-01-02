import java.util.Scanner;

public class SwitchCasePractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert command");
        String command = scanner.nextLine();
        String[] stringSplit = command.split("\\s+");

        if (stringSplit[0].toLowerCase().equals("indent")) {
            System.out.println("indent with " + stringSplit[1]);
        } else {
            switch (command.toLowerCase()) {
                case ".paragraph":
                    System.out.println("set paragraph");
                    break;

                case ".fill":
                    System.out.println("set indent");
                    break;

                case ".nofill":
                    System.out.println("set nofill");
                    break;

                case ".regular":
                    System.out.println("regular");
                    break;

                case ".italic":
                    System.out.println("italic");
                    break;

                case ".indent %d":
                    System.out.println("indent with " + command);
                    break;

                case ".bold":
                    System.out.println("bold");
                    break;
            }
        }
    }
}
