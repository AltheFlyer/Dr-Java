/**
 * Selection5.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The fifth selection activity
 */

import java.util.Scanner;

public class Selection5 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your mark: >>>");
    int mark = input.nextInt();
    if (mark > 100 || mark < 0) {
      System.out.println("Invalid");
    } else if (mark <= 100 && mark >= 75) {
      System.out.println("Great");
    } else if (mark >= 50) {
      System.out.println("Pass");
    } else if (mark >= 0) {
      System.out.println("Fail");
    }
  }
}