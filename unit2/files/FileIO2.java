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
        
        int numWords = readFromFile("read.txt");
       
        System.out.println(numWords);
    }
    
    /**
     * readFromFile
     * @param filename The name of the file to read from
     * @param int, the number of words in that file
     */
    public static int readFromFile(String filename) throws IOException {
        int words = 0;
        Scanner input = new Scanner(new File(filename));
        while (input.hasNext()) {
            input.next();
            ++words;
        }
        return words;
    }
}