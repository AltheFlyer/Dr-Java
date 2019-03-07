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
        PrintWriter output;
        Scanner fileIn;
        Scanner input = new Scanner(System.in);
        String file;
        String command;
        String password;
        
        System.out.println("Would you like to encode or decode?");
        command = input.nextLine();
        System.out.println("Enter the name of the file (no extensions)");
        file = input.nextLine();
        if (command.equals("encode")) {
            fileIn = new Scanner(new File(file + ".txt"));
            output = new PrintWriter(new File(file + ".coded"));
            password = fileIn.nextLine();
            output.print(encode(password));
            output.close();
            System.out.println(encode(password));
        }
        if (command.equals("decode")) {
            fileIn = new Scanner(new File(file + ".coded"));
            output = new PrintWriter(new File(file + ".txt"));
            password = fileIn.nextLine();
            output.print(decode(password));
            output.close();
            System.out.println(decode(password));
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
}