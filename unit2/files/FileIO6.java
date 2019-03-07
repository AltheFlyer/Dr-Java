import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO6.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Writes an address to a file
 */

public class FileIO6 {
    
    public static void main(String[] args) throws IOException {
        PrintWriter output;
        Scanner input = new Scanner(System.in);
        String filename, scannerName, message;
        
        System.out.println("Enter the name of the file");
        filename = input.nextLine();
        
        System.out.println("Do you want a scanner? (Y/N)");
        if (input.nextLine().equals("Y")) {
            System.out.println("Enter the name of the scanner");
            scannerName = input.nextLine();
        } else {
            scannerName = "";
        }
        
        System.out.println("Enter a message to output");
        message = input.nextLine();
        
        output = new PrintWriter(new File(filename + ".java"));
        if (scannerName.length() > 0) {
            output.println("import java.util.Scanner;");
        }
        output.printf("public class %s {\n", filename);
        output.printf("\tpublic static void main(String[] args) {\n");
        if (scannerName.length() > 0) {
            output.printf("\t\tScanner %s = new Scanner(System.in);\n", scannerName);
        }
        
        output.printf("\t\tSystem.out.println(\"%s\");\n", message);
        output.println("\t}\n}");
        output.close();
    }
}