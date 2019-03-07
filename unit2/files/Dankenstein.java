import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Dankenstein.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 7, 2019
 * Counts the letter occurances in a file
 */

public class Dankenstein {
    
    public static void main(String[] args) throws IOException {
        String allText = readFile("Frankenstein.txt");
        String letters = "abcdefghijklmnopqrstuvwxyz";
        int[] chars = getCharCount(allText);
        
        int[] arr = sortIndices(chars);
        for (int i = 0; i < 10; ++i) {
            System.out.printf("%s: %s\n", letters.charAt(arr[i]), chars[arr[i]]);
        }
    }
    
    /**
     * sortIndices
     * @param arr The array of ints
     * @return int[], the list's indices, nordered based on their contents
     */
    public static int[] sortIndices(int[] arr) {
        int[] orderedIndices = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            orderedIndices[i] = i;
        }
        int point = 1;
        while (point > 0) {
            point = 0;
            for (int i = 1; i < arr.length; ++i) {
                if (arr[orderedIndices[i]] > arr[orderedIndices[i - 1]]) {
                   int tmp = orderedIndices[i];
                   orderedIndices[i] = orderedIndices[i - 1];
                   orderedIndices[i - 1] = tmp;
                   point = i;
                }
            }
        }
        return orderedIndices;
    }
    
    /**
     * readFile
     * @param name The name of the file to read from
     * @return String, the text contained within the file
     */
    public static String readFile(String name) throws IOException {
        Scanner input = new Scanner(new File(name));
        String out = "";
        while (input.hasNext()) {
            out += input.nextLine();
        }
        return out;
    }
    
    /**
     * getCharCount
     * @param s The string to count characters in
     * @return int[], the array of the character counts from a (0) to z (25)
     */
    public static int[] getCharCount(String s) {
        int[] charCount = new int[26];
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; ++i) {
            charCount[i] = 0;
        }
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); ++i) {
            if (letters.indexOf(s.charAt(i)) > -1) {
                charCount[letters.indexOf(s.charAt(i))]++;
            }
        }
        return charCount;
    }
}