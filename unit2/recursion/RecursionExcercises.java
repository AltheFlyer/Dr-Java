/**
 * 
 * 
 * 
 * 
 * 
 */

public class RecursionExcercises {
    
    public static void main(String[] args) {
        
    }
    
    public int factorial(int n) {
        if (n <= 1) {
            return n;
        } else {
            return n * factorial(n-1);
        }
    }
    
    public int bunnyEars(int bunnies) {
        if (bunnies >= 1) {
            return 2 + bunnyEars(bunnies - 1);
        }
        return 0;
    }

    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci (n - 2);
    }

    public int bunnyEars2(int bunnies) {
        if (bunnies == 0) {
            return 0;
        }
        if (bunnies % 2 == 0) {
            return 5 + bunnyEars2(bunnies - 2);
        }
        return 2 + bunnyEars2(bunnies - 1);
    }

    public int triangle(int rows) {
        if (rows == 0) {
            return 0;
        }
        return rows + triangle(rows - 1);
    }

    public int sumDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + sumDigits(n / 10);
    }

    public int count7(int n) {
        if (n == 0) {
            return 0;
        }
        if (n % 10 == 7) {
            return 1 + count7(n / 10);
        } 
        return count7(n / 10);
    }

    public int count8(int n) {
        if (n == 0) {
            return 0;
        }
        if (n % 100 == 88) {
            return 2 + count8(n / 10);
        }
        if (n % 10 == 8) {
            return 1 + count8(n / 10);
        }
        return count8(n / 10);
    }

    public int powerN(int base, int n) { 
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }
        return base * powerN(base, n-1);
    }
    
    public int countX(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.charAt(0) == 'x') {
            return 1 + countX(str.substring(1));
        }
        return countX(str.substring(1));
    }

    public int countHi(String str) {
        int r = str.indexOf("hi");
        if (r == -1) {
            return 0;
        }
        return 1 + countHi(str.substring(r + 1));
    }

    public String changeXY(String str) {
        int r = str.indexOf('x');
        if (r == -1) {
            return str;
        }
        return str.substring(0, r) + 'y' + changeXY(str.substring(r + 1));
    }

    public String changePi(String str) {
        int r = str.indexOf("pi");
        if (r == -1) {
            return str;
        }
        return str.substring(0, r) + "3.14" + changePi(str.substring(r + 2));
    }

    public String noX(String str) {
        int r = str.indexOf("x");
        if (r == -1) {
            return str;
        }
        return str.substring(0, r) + noX(str.substring(r + 1));
    }

    public boolean array6(int[] nums, int index) {
        if (index >= nums.length) {
            return false;
        }
        if (nums[index] == 6) {
            return true;
        }
        return array6(nums, index + 1);
    }
    
    public int array11(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        if (nums[index] == 11) {
            return 1 + array11(nums, index + 1);
        }
        return array11(nums, index + 1);
    }
    
    public boolean array220(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return false;
        }
        return nums[index] * 10 == nums[index + 1] || array220(nums, index + 1);
    }

    public String allStar(String str) {
        if (str.length() <= 1) {
            return str; 
        }
        return str.charAt(0) + "*" + allStar(str.substring(1));
    }
    
    public String pairStar(String str) {
        if (str.length() <= 1) {
            return str;
        }
        if (str.charAt(0) == str.charAt(1)) {
            return str.charAt(0) + "*" + pairStar(str.substring(1));
        }
        return str.charAt(0) + pairStar(str.substring(1));
    }

    public String endX(String str) {
        if (str.length() <= 1) {
            return str;
        }
        if (str.charAt(0) == 'x') {
            return endX(str.substring(1)) + "x";
        }
        return str.charAt(0) + endX(str.substring(1));
    }

    public int countPairs(String str) {
        if (str.length() <= 2) {
            return 0;
        }
        if (str.charAt(0) == str.charAt(2)) {
            return 1 + countPairs(str.substring(1));
        }
        return countPairs(str.substring(1));
    }
    
    public int countAbc(String str) {
        if (str.length() <= 2) {
            return 0;
        }
        if (str.substring(0, 3).equals("abc") || str.substring(0, 3).equals("aba")) {
            return 1 + countAbc(str.substring(2));
        }
        return countAbc(str.substring(1));
    }

    public int count11(String str) {
        if (str.length() <= 1) {
            return 0;
        }
        if (str.substring(0, 2).equals("11")) {
            return 1 + count11(str.substring(2));
        }
        return count11(str.substring(1));
    }
    
    public String stringClean(String str) {
        if (str.length() <= 1) {
            return str;
        }
        if (str.charAt(0) == str.charAt(1)) {
            return stringClean(str.substring(1));
        }
        return str.charAt(0) + stringClean(str.substring(1));
    }

    public int countHi2(String str) {
        if (str.length() <= 1) {
            return 0;
        }
        if (str.substring(0, 2).equals("xh")) {
            return countHi2(str.substring(2));
        }
        if (str.substring(0, 2).equals("hi")) {
            return 1 + countHi2(str.substring(2));
        }
        return countHi2(str.substring(1));
    }
    
    public String parenBit(String str) {
        if (str.charAt(0) != '(') {
            return parenBit(str.substring(1));
        }
        if (str.charAt(str.length() - 1) != ')') {
            return parenBit(str.substring(0, str.length() - 1));
        }
        return str;
    }

    public boolean nestParen(String str) {
    if (str.length() == 0) {
      return true;
    }
    if (str.length() < 2) {
      return false;
    } 
    if (str.equals("()")) {
        return true;
    }
    if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
        return nestParen(str.substring(1, str.length() - 1));
    }
    if (str.charAt(0) != '(') {
        return nestParen(str.substring(1, str.length()));
    }
    return nestParen(str.substring(0, str.length() - 1));
    }
    
    public int strCount(String str, String sub) {
        if (str.length() < sub.length()) {
            return 0;
        }
        if (str.substring(0, sub.length()).equals(sub)) {
            return 1 + strCount(str.substring(sub.length()), sub);
        }
        return strCount(str.substring(1), sub);
    }
    
    public boolean strCopies(String str, String sub, int n) {
        if (n == 0) {
            return true;
        }
        if (str.length() < sub.length()) {
            return false;
        }
        if (str.substring(0, sub.length()).equals(sub)) {
            if ( n == 1) {
                return true;
            }
            return strCopies(str.substring(1), sub, n-1);
        }
        return strCopies(str.substring(1), sub, n);
    }
    
    public int strDist(String str, String sub) {
        if (str.length() < sub.length()) {
            return 0;
        }
        
        if (str.substring(0, sub.length()).equals(sub) && str.substring(str.length() - sub.length()).equals(sub)) {
            return str.length();
        }
        
        if (str.substring(0, sub.length()).equals(sub)) {
            return strDist(str.substring(0, str.length() - 1), sub);
        }
        
        return strDist(str.substring(1), sub);
    }
    
}