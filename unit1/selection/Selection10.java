/**
 * Selection10.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The ten selection activity - based on a flowchart
 */

import java.util.Scanner;

public class Selection10 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String response;
    System.out.println("Do you have $200,000 to $500,000");
    if (input.nextLine().equals("yes")) {
      System.out.println("Do you get to places quickly?");
      if (input.nextLine().equals("yes")) {
        System.out.println("Purchase a Lamborghini Aventador");
      } else {
        System.out.println("Purchase Rolls-Royce Phantom");
      }
    } else {
      System.out.println("Do you have $100,000 to $200,000?");
      if (input.nextLine().equals("yes")) {
        System.out.println("Do you like imports?");
        if (input.nextLine().equals("yes")) {
          System.out.println("Puchase a Nissan GTR");
        } else {
          System.out.println("Purchase a Dodge Viper SRT");
        }
      } else {
        System.out.println("Purchase a Honda Fit");
      }
    }
  }
}