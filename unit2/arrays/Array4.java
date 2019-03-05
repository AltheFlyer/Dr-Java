import java.util.Scanner;

/**
 * Array4.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 5, 2019
 * The fourth array exercise
 */

public class Array4 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] nums = new int[10];
        
        System.out.println("Enter 10 ints");
        
        for (int i = 0; i < 10; ++i) {
            nums[i] = input.nextInt();
        }
        
        bubbleSort(nums);
        
        for (int i = 0; i < 10; ++i) {
            System.out.println(nums[i]);
        }
    }
    
    public static void bubbleSort(int[] arr) {
        int switchIndex = 1;
        while (switchIndex != 0) {
            switchIndex = 0;
            for (int i = 1; i < arr.length; ++i) {
                if (arr[i] < arr[i-1]) {
                    swap(arr, i, i-1);
                    switchIndex = i-1;
                }
            }
        }
    }
    
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}