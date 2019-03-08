import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rewriter {

    public static void main(String[] args) throws FileNotFoundException {
        String javaText = readFromFile("Rewriter.java");
        String name = randString(10);
        writeToFile(name + ".java", javaText.replace("Rewriter", name));
    }

    public static String readFromFile(String filename) throws FileNotFoundException {
        String out = "";
        Scanner input = new Scanner(new File(filename));
        while (input.hasNextLine()) {
            out += input.nextLine() + "\n";
        }
        input.close();
        return out;
    }

    public static void writeToFile(String filename, String text) throws FileNotFoundException {
        PrintWriter output = new PrintWriter(new File(filename));
        output.print(text);
        output.close();
    }
    
    public static String randString(int length) {
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        String out = "";
        for (int i = 0; i < length; ++i) {
            out += letters.charAt((int)(Math.random() * 26));
        }
        return out;
    }
}
