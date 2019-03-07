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
        PrintWriter output = new PrintWriter(new File("address.txt"));
        Scanner input = new Scanner(System.in);
        String address = "";
        
        System.out.println("Enter your address:");
        address = input.nextLine();
        
        output.println(address);
        output.close();
    }
}