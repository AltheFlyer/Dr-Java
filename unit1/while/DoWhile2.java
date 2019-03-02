/**
 * DoWhile2.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The second do while excercise
 */

public class DoWhile2 {
  
  public static void main(String[] args) {
    //Default value = 0
    int counter1, counter2;
    counter1 = 0;
    counter2 = 0;
    int a = (int) (Math.random() * 100);
    int b = (int) (Math.random() * 100);
    int SUM = a + b;
    int PRODUCT = a * b;
    int newSum = 0;
    int newProduct = 0;
    int attempts = 0;
    do {
      counter2 = 0;
      counter1++;
      do {
        counter2++;
        newSum = counter1 + counter2;
        newProduct = counter1 * counter2;
        attempts++;
      } while (counter2 < 100 && !(newSum == SUM && newProduct == PRODUCT));
    } while (counter1 < 100 && !(newSum == SUM && newProduct == PRODUCT));
    System.out.println(counter1);
    System.out.println(counter2); 
    System.out.println(attempts);
  }
}