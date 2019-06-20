/**
 * [FinalTester.java]
 * testing the answers to a bunch of cs exam problems
 * @version 1.0
 * @author Allen Liu
 * @since June 20, 2019
 */
public class FinalTester {
  
  public static void main(String[] args) {
    //Consecutive sum array
    //true, true, true, false
    System.out.println("Consecutive sum arrays");
    System.out.println(checkConsecutiveSum(new int[] {2, 2, 4, 8, 16}));
    System.out.println(checkConsecutiveSum(new int[] {1, 1, 2}));
    System.out.println(checkConsecutiveSum(new int[] {23, 23, 46, 92}));
    System.out.println(checkConsecutiveSum(new int[] {1, 2, 3, 4}));
    System.out.println();
    
    //Error testing for string range
    //Mdu, you're safe since checking index (length(), length()) does not error
    //The check fails if any given index in substring is greater than the length of the string
    System.out.println("String Error Check #1");
    int testNumber = 0;
    String[] strings = {"abc", "ab", "a", ""};
    try {
      for (testNumber = 0; testNumber < strings.length; ++testNumber) {
        strings[testNumber].substring(strings[testNumber].length(), strings[testNumber].length());
        System.out.println(strings[testNumber] + " passes");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(testNumber + " FAILED");
    }
    System.out.println();
    
    //This will fail due to out of bounds
    System.out.println("String Error Check #2");
    try {
      for (testNumber = 0; testNumber < strings.length; ++testNumber) {
        System.out.println(strings[testNumber].substring(1, 1));
        System.out.println(strings[testNumber] + " passes");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(testNumber + " FAILED");
    }
    System.out.println();
    
  }
  
  /**
   * [checkConsecutiveSum]
   * checks if an array of ints is made of consecutive sums
   * @param nums the array of ints
   * @return boolean, whether the array is made of consecutive sums or not
   */
  public static boolean checkConsecutiveSum(int[] nums) {
    //Too few items for consecutive sums
    if (nums.length <= 1) {
      return false;
      //First and second item in the array should be equal
    } else if (nums.length == 2) {
      return (nums[1] == nums[0]);
    } else {
      //The last element will always be twice the previous when the array length > 2
      if (nums[nums.length - 1] == 2 * nums[nums.length - 2]) {
        int[] sub = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; ++i) {
          sub[i] = nums[i];
        }
        return checkConsecutiveSum(sub);
      }
      return false;
    }                   
  }
}