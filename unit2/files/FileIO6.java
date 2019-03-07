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
        Scanner input = new Scanner(System.in);
        String out = "";
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
        
        if (scannerName.length() > 0) {
            out += "import java.util.Scanner;\n";
        }
        
        out += String.format("public class %s {\n", filename);
        out += String.format("\tpublic static void main(String[] args) {\n");
        if (scannerName.length() > 0) {
            out += String.format("\t\tScanner %s = new Scanner(System.in);\n", scannerName);
        }
        
        out += String.format("\t\tSystem.out.println(\"%s\");\n", message);
        out += "\t}\n}";
        writeToFile(filename + ".java", out);
    }
    
    /**
     * writeToFile
     * @param filename The name of the file to write to
     * @param s The string to write to the file
     */
    public static void writeToFile(String filename, String s) throws IOException {
        PrintWriter output = new PrintWriter(new File(filename));
        output.print(s);
        output.close();
    }
}