/**
 * For5.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 14, 2019
 * the fifth for loop excercise
 */

import java.util.Scanner;

public class For5 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a word: >>>");
    String word = input.nextLine();
    System.out.println("Enter the number of times to repeat: >>>");
    int times = input.nextInt();
    
    for (int i = 0; i < times; ++i) {
      System.out.println(word);
    }
  }
}