/**
 * Selection7.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The seventh selection activity
 */

import java.util.Scanner;

public class Selection7 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a mark: >>>");
    int firstMark = input.nextInt();
    System.out.println("Enter another mark: >>>");
    int secondMark = input.nextInt();
    if (firstMark >= secondMark) {
      System.out.println(firstMark); 
    } else if (secondMark > firstMark) {
      System.out.println(secondMark);
    }
    
  }
}