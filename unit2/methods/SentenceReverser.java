import java.util.Scanner;

/**
 * SentenceReverser.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1
 * Reverses a sentence!
 */
public class SentenceReverser {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a sentence");
        String sentence = input.nextLine();
        
        System.out.println(reverse(sentence));
        input.close();
    }
    
    /**
     * reverse
     * reverses the words in a sentence
     * @param s The sentence
     * @retun String, the sentence but with words in opposite order
     */
    public static String reverse(String s) {
        String out = "";
        while (s.indexOf(" ") > -1) {
            out += s.substring(s.lastIndexOf(" ") + 1) + " ";
            s = s.substring(0, s.lastIndexOf(" "));
        }
        out += s;
        return out;
    }
}