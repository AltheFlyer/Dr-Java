import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO4.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Deals with better encoding
 */

public class FileIO4 {
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String file;
        String command;
        String password;
        
        System.out.println("Would you like to encode or decode?");
        command = input.nextLine();
        System.out.println("Enter the name of the file (no extensions)");
        file = input.nextLine();
        if (command.equals("encode")) {
            password = readFromFile(file + ".txt");
            writeToFile(file + ".coded", encode(password));
        }
        if (command.equals("decode")) {
            password = readFromFile(file + ".coded");
            writeToFile(file + ".txt", decode(password));
        }
    }
    
    public static String encode(String s) {
        String coder = " abcdefghijklmnopqrstuvwxyz";
        String out = "";
        for (int i = 0; i < s.length(); ++i) {
            if (coder.indexOf(s.charAt(i)) > -1) {
                out += coder.indexOf(s.charAt(i)) + " ";
            } else {
                out += s.charAt(i) + " ";
            }
        }
        return out;
    }
    
    public static String decode(String s) {
        String coder = " abcdefghijklmnopqrstuvwxyz";
        String out = "";
        while (s.indexOf(" ") > -1) {
            String token = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf(" ") + 1);
            try {
                out += coder.charAt(Integer.parseInt(token));
            } catch (Exception e) {
                out += token;
            }
        }
        return out;
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