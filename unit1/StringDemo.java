/** [StringDemo.java]
  * This program demonstrates use of the Scanner and a variety of String methods
  * @author Mangat
  * @version 1.0
  */

/* import statements */
import java.util.Scanner;  //This scanner is used to get input from the user


class StringDemo { 

  /** *** main method *** */
  public static void main(String[] args){
      
    Scanner input = new Scanner(System.in); //create a scanner called input
    String message; // create a string to store user input
  

    System.out.print("Please enter your full name: "); 
    message = input.nextLine(); //ask the user for their FULL name an store it
    
    /* Begin demonstrating some string methods */
    
    // .length() returns the number of characters in a string
    System.out.println("Testing .length() " + message.length() );
    
    // indexOf(String) returns the location of the start of the specified string. 
    // A -1 is returned if the string is not found
    System.out.println("Testing .indexOf(a) " + message.indexOf("a") );
    
    // lastIndexOf(String) returns the location of the start of the specified string
    // If multiple string are found this will be the last one
    System.out.println("Testing .lastIndexOf(a) " + message.lastIndexOf("a") );
    
    // .charAt(int) returns the CHARACTER (not string) that is found at a specific location
    System.out.println("Testing .charAt(2) " + message.charAt(2));
    
    // .subString(int,int) will give return a string that starts at the first int and ends at the second
    System.out.println("Testing .substring(1,4) " + message.substring(1,4) );
    
    // .subString(int) will give return a string that starts at the first int until the end 
    System.out.println("Testing .substring(3) " + message.substring(3) );
    
    // .toUpperCase() will return a string that is all captials
    // a lower case method also exists
    System.out.println("Testing .toUpperCase() " + message.toUpperCase() );
    
    
    // .compareTo(string) will determine if the string is alphabetically before, after, or equal to the string specified
    System.out.println("Testing .compareTo(apple) " + message.compareTo("apple") );
    
    /* There are many methods in the String class.
       Refer to the Oracle documentation to discover more:
       https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
    */
    
    
  } //end of main
} //end of class