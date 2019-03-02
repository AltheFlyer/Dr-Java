/**
 * Selection9.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The ninth selection activity
 */

import java.util.Scanner;

public class Selection9 {
 
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String wordA, wordB, wordC;
    System.out.println("Enter 3 words: >>>");
    wordA = input.next();
    System.out.print(">>>");
    wordB = input.next();
    System.out.print(">>>");
    wordC = input.next();
    //Orders A and B
    if (wordA.compareTo(wordB) > 0) {
      String tmp = wordA;
      wordA = wordB;
      wordB = tmp;
    }
    //Orders A and C
    if (wordA.compareTo(wordC) > 0) {
      String tmp = wordA;
      wordA = wordC;
      wordC = tmp;
    }
    //Orders B and C
    if (wordB.compareTo(wordC) > 0) {
      String tmp = wordB;
      wordB = wordC;
      wordC = tmp;
    }
    
    System.out.println(wordA);
    System.out.println(wordB);
    System.out.println(wordC);
  }
}