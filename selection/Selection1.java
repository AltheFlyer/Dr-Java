/**
 * Selection1.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The first selection activity
 */

import java.util.Scanner;

public class Selection1 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your age: >>>");
    int age = input.nextInt();
    if (age >= 65) {
      System.out.println("You are a senior citizen");
    }
  }
}