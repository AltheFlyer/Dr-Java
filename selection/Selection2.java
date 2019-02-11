/**
 * Selection2.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The second selection activity
 */

import java.util.Scanner;

public class Selection2 {
 
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your mark: >>>");
    int mark = input.nextInt();
    if (mark >= 50) {
      System.out.println("you passed");
    } else {
      System.out.println("better luck next time");
    }
  }
}