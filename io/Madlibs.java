/**
 * Madlibs.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 5, 2019
 * A Madlibs that uses string format help me
 */

package io;

import java.util.Scanner;

public class Madlibs {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String text = input.nextLine();
    int counter = 0;
    for (int i = 1; i < text.length(); ++i) {
      if (text.charAt(i-1) == '%' && text.charAt(i) == 's') {
        ++counter;
      }
    }
    String[] tokens = new String[counter];
    for (int i = 0; i < counter; ++i) {
      tokens[i] = input.nextLine();
    }
    
    System.out.printf(text, tokens);
  }
}