/**
 * [StringActivity.java]
 * String activities and challenges
 * @version 1.0
 * @author Allen Liu
 * @since Feb 7, 2019
 */

import java.util.Scanner;

public class StringActivity{
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    /*
    //Activity 1
    System.out.print("Enter a sentence: >>>");
    System.out.println(input.nextLine().length());
    
    //Activity 2
    System.out.print("Enter a sentence: >>>");
    String text = input.nextLine();
    text = text.charAt(0) + text.substring(1).toLowerCase();
    System.out.println(text);
    
    //Activity 3
    System.out.println("Enter the first string: >>>");
    String firstString = input.nextLine();
    System.out.println("Enter the second string: >>>");
    String secondString = input.nextLine();
    System.out.println(firstString.compareTo(secondString));
    
    */
    /*
    //Activity 4
    System.out.println("Enter a 5 word sentence: >>>");
    String wordSentence = input.nextLine();
    String out = wordSentence.substring(0, wordSentence.indexOf(' '));
    System.out.println(out);
    wordSentence = wordSentence.substring(wordSentence.indexOf(' ') + 1);
    out = wordSentence.substring(0, wordSentence.indexOf(' '));
    System.out.println(out);
    wordSentence = wordSentence.substring(wordSentence.indexOf(' ') + 1);
    out = wordSentence.substring(0, wordSentence.indexOf(' '));
    System.out.println(out);
    wordSentence = wordSentence.substring(wordSentence.indexOf(' ') + 1);
    out = wordSentence.substring(0, wordSentence.indexOf(' '));
    System.out.println(out);
    wordSentence = wordSentence.substring(wordSentence.indexOf(' ') + 1);
    System.out.println(wordSentence);
    */
    //Challenge
    System.out.println("Enter a 10 word sentence: >>>");
    String secretSentence = input.nextLine();
    String secret = "";
    //Take first letter of string
    secret += secretSentence.charAt(0);
    //Cut off first word (and space)
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    //Repeat for all 10 words
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    secretSentence = secretSentence.substring(secretSentence.indexOf(' ') + 1);
    secret += secretSentence.charAt(0);
    
    
    System.out.println("Message: " + secret);
    
    //Close stream
    input.close();
  }
}