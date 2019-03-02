/**
 * While1.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The first while excercise
 */

import java.util.Scanner;

public class While1 {
  
  public static void main(String[] args) {
    String PASSWORD = "ADMIN";
    Scanner input = new Scanner(System.in);
    while (!input.nextLine().equals(PASSWORD)) {
      System.out.println("Error - incorrect password");
    }
    System.out.println("Password Accepted");
  }
}