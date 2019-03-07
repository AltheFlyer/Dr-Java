import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO3.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Asks for a password
 */

public class FileIO3 {
    
    public static void main(String[] args) throws IOException {
        File passwordStorage = new File("password.txt");
        Scanner fileInput = new Scanner(passwordStorage);
        Scanner input = new Scanner(System.in);
        PrintWriter writer;
        
        String password = fileInput.nextLine();
        System.out.println(password);
        
        System.out.println("Enter the password");
        if (input.nextLine().equals(password)) {
            System.out.println("Successful login");
            System.out.println("Would you like to change the password? (Y/N)");
            if (input.nextLine().equals("Y")) {
                System.out.println("Enter the new password");
                writer = new PrintWriter(passwordStorage);
                writer.println(input.nextLine());
                writer.close();
            }   
            System.out.println("Quitting.");
        } else {
            System.out.println("Wrong code!");
        }
    }
}