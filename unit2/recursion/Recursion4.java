/**
 * Recursion4.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 19, 2019
 * 
 */

public class Recursion4 {
    
    public static void main(String[] args) {
        /*
        int val = 14614;  
        val = 211;
        outputNumericPalindromes(val, 0);
        */
        int[] nums = {1234, 121, 2424, 14614};
        for (int i = 0; i < nums.length; ++i) {
            getCombos(nums[i], 0);
            System.out.println();
        }
    }
    
    public static void outputNumericPalindromes(int number, int index) {
        if (isPalindrome(number)) {
            System.out.println(number);
        }
    }
    
    public static void getCombos(int n, int plus) {
        int digits = 1;
        int pow = 10;
        int base = 1;
        for (int i = 10; i <= n; i *= 10) {
            ++digits;
            pow *= 10;
            base *= 10;
        }
        //1 digit number:
        if (digits == 1) {
            System.out.println(n + plus);
        }
        //2 digit number:
        if (digits == 2) {
            int top = n / 10;
            int bot = n % 10;
            if (isPalindrome(top * 10 + bot + plus)) {
                System.out.println(top * 10 + bot + plus);
            }
            if (isPalindrome(bot * 10 + top + plus)) {
                System.out.println(bot * 10 + top + plus);
            }
        }
        //3+ Digit number:
        if (digits >= 3) {
            //Use first digit as first place
            for (int i = 0; i < 3; ++i) {
                int top = n / pow;
                int bot = n % (pow / 10);
                getCombos(top * (pow / 10) + bot, n % pow / (pow /10) * base + plus);
                pow /= 10;
            }
        }
        
        //System.out.printf("Digits: %d, Add: %d\n", digits, plus);
    }
    
    
    public static boolean isPalindrome(int number) {
        int k = 0;
        int n = number;
        while (n > 0) {
            k *= 10;
            k += n % 10;
            n /= 10;
        }
        return k == number;
    }
}