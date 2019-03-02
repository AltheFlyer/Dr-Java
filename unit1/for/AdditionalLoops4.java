/**
 * AdditionalLoops4.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 14, 2019
 * the fourth additional excercise
 */

import java.util.Scanner;

public class AdditionalLoops4 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    String word = input.nextLine();
    String reverse = "";

    for (int i = word.length() - 1; i >= 0; --i) {
      reverse += word.charAt(i);
    }
    
    System.out.println(word.equals(reverse));
  }
}