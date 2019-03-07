import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO2.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Reads the number of words in a file
 */

public class FileIO2 {
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("read.txt"));
        int words = 0;
        
        while (input.hasNext()) {
            input.next();
            ++words;
        }
        input.close();
        
        System.out.println(words);
    }
}