/**
 * DoWhile1.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The first do while excercise
 */

import java.util.Scanner;

public class DoWhile1 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String PASSWORD = "ADMIN";
    String in;
    
    //Do while makes code slightly clearer, but makes code longer
    do {
      in = input.nextLine();
      if (!in.equals(PASSWORD)) {
        System.out.println("Error - incorrect password");
      }
    } while (!in.equals(PASSWORD));
    
    System.out.println("Password Accepted");
  }
}