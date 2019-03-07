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
        Scanner input = new Scanner(System.in);
        
        String password = readFromFile("password.txt");
        
        System.out.println("Enter the password");
        if (input.nextLine().equals(password)) {
            System.out.println("Successful login");
            System.out.println("Would you like to change the password? (Y/N)");
            if (input.nextLine().equals("Y")) {
                System.out.println("Enter the new password");
                writeToFile("password.txt", input.nextLine());
            }   
            System.out.println("Quitting.");
        } else {
            System.out.println("Wrong code!");
        }
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
    
    /**
     * readFromFile
     * @param filename The name of the file to read from
     * @param int, the number of words in that file
     */
    public static String readFromFile(String filename) throws IOException {
        String text = "";
        Scanner input = new Scanner(new File(filename));
        while (input.hasNextLine()) {
            text += input.nextLine();
        }
        return text;
    }
}