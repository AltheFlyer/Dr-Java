import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * FileIO1.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Writes webpages yay
 */

public class FileIO5 {
    
    public static void main(String[] args) throws IOException {
        PrintWriter output = new PrintWriter(new File("webpage.html"));
        Scanner input = new Scanner(System.in);
        String html = "<html>\n" +
            "<head>\n" +
            "<meta charset=\"utf-8\">\n" +
            "<title>%s</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>%s</h1>\n" +
            "<p>%s</p>\n" +
            "</body>\n" +
            "</html>";
        
        System.out.println("Enter the title");
        String title = input.nextLine();
        
        System.out.println("Enter the Main Header");
        String header = input.nextLine();
        
        System.out.println("Enter a paragraph");
        String para = input.nextLine();
        
        output.println(String.format(html, title, header, para));
        output.close();               
    }
}