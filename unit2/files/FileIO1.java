import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO1.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Writes an address to a file
 */

public class FileIO1 {
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String address = "";
        
        System.out.println("Enter your address:");
        address = input.nextLine();
        
        writeToFile("address.txt", address);
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