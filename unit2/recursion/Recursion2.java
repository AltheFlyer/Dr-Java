

public class Recursion2 {
    
    /**
     * 
     */
    public boolean groupSum(int start, int[] nums, int target) {
        if (start >= nums.length) {
            return target == 0;   
        }
        return groupSum(start + 1, nums, target - nums[start]) || groupSum(start + 1, nums, target);
    }
    
    public boolean groupSum6(int start, int[] nums, int target) {
        if (start >= nums.length) {
            return target == 0;   
        }
        if (nums[start] == 6) {
            return groupSum6(start + 1, nums, target - nums[start]);
        }
        return groupSum6(start + 1, nums, target - nums[start]) || groupSum6(start + 1, nums, target);
    }
    
    public boolean groupSum5(int start, int[] nums, int target) {
        if (start >= nums.length) {
            return target == 0;   
        }
        
        if (nums[start] % 5 == 0) {
            return groupSum5(start + 1, nums, target - nums[start]);    
        }
        if (nums[start] == 1 && start > 0 && nums[start - 1] % 5 == 0) {
            return groupSum5(start + 1, nums, target);
        }
        return groupSum5(start + 1, nums, target - nums[start]) || groupSum5(start + 1, nums, target);
    }

    public boolean groupSumClump(int start, int[] nums, int target) {
        if (start >= nums.length) {
            return target == 0;
        }
        int setLength = 0;
        for (int i = start; i < nums.length; ++i) {
            if (nums[i] == nums[start]) {
                setLength++;
            } else {
                break;
            }
        }
        return groupSumClump(start + setLength, nums, target - nums[start] * setLength) || groupSumClump(start + setLength, nums, target);
    }

    public boolean splitArray(int[] nums) {
        return checkArray(0, nums, 0, 0);
    }
    
    public boolean checkArray(int start, int[] nums, int a, int b) {
        if (start >= nums.length) {
            return a == b;
        }
        return checkArray(start + 1, nums, a + nums[start], b) || checkArray(start + 1, nums, a, b + nums[start]);
    }
    
    public boolean splitOdd10(int[] nums) {
        return checkArray(0, nums, 0, 0);
    }
    public boolean checkArray(int start, int[] nums, int a, int b) {
        if (start >= nums.length) {
            return (a % 10 == 0 && b % 2 == 1) || (a % 2 == 1 && b % 10 == 0);
        }
        return checkArray(start + 1, nums, a + nums[start], b) || checkArray(start + 1, nums, a, b + nums[start]);
    }
    
    public boolean split53(int[] nums) {
        return checkArray(0, nums, 0, 0);
    }
    public boolean checkArray(int start, int[] nums, int a, int b) {
        if (start >= nums.length) {
            return a == b;
        }
        if (nums[start] % 5 == 0) {
            return checkArray(start + 1, nums, a + nums[start], b);
        }  else if (nums[start] % 3 == 0) {
            return checkArray(start + 1, nums, a, b + nums[start]);
        }
        return checkArray(start + 1, nums, a + nums[start], b) || checkArray(start + 1, nums, a, b + nums[start]);
    }
    
}