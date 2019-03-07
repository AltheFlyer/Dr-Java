import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO7.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Dr Dankenstein enabled
 */

public class FileIO7 {
    
    public static void main(String[] args) throws IOException {
        Scanner fileIn = new Scanner(new File("Frankenstein.txt"));
        PrintWriter output = new PrintWriter(new File("Dankenstein.txt"));
        String line;
        int counter = 0;
        String toReplace = "Frankenstein";
        String replaceWith = "Dankenstein";
        
        while (fileIn.hasNext()) {
            line = fileIn.nextLine();
            while (line.indexOf(toReplace) > -1) {
                line = line.substring(0, line.indexOf(toReplace)) + replaceWith + line.substring(line.indexOf(toReplace) + toReplace.length());
                counter++;
            }
            output.println(line);
        }
        System.out.println(counter);        
        
        output.close();
    }
}