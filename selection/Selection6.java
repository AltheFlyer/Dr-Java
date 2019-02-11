/**
 * Selection6.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The sixth selection activity
 */

import java.util.Scanner;

public class Selection6 {
 
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String password = "happy";
    if (input.nextLine().equals(password)) {
      System.out.println("you are logged in");
    } else {
      System.out.println("incorrect password");
    }
  }
}