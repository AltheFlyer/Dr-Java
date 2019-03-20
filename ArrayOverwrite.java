public class ArrayOverwrite {
    
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3};
        int[] hhh = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        arr = getC();
        
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
        System.out.println();
        arr = hhh;
        
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
        System.out.println();
        arr[4] = 1000;
        
        for (int i = 0; i < hhh.length; ++i) {
            System.out.println(hhh[i]);
        }
    }
    
    public static int[] getC() {
        int[] k = new int[3];
        k[0] = 1;
        k[1] = 2;
        k[2] = 12;
        return k;
    }
}