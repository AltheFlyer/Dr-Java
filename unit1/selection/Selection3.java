/**
 * Selection3.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The third selection activity based on temperature
 */

import java.util.Scanner;

public class Selection3 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a temperature: >>>");
    int temperature = input.nextInt();
    if (temperature > 30) {
      System.out.println("hot");
    } else if (temperature >= 20) {
      System.out.println("comfortable");
    } else if (temperature >= 10) {
      System.out.println("cool");
    } else {
      System.out.println("cold");
    }
  }
}