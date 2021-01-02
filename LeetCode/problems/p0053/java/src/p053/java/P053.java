//[IN PROGRESS]

package p053;

import java.util.Random;
import mathtools.MF;

public class P053 {

    public static void main(String[] args) {
        Solution s = new Solution();
        Random rng = new Random(System.currentTimeMillis());
        for (int size = 10; size <= 100000; size *= 10) {
            int[] arr = new int[size];
            for (int i = 0; i < arr.length; i++)
                arr[i] = rng.nextInt(20) - 10;
            
            MF.startTimer();
            int max = s.maxSubArray(arr);
            System.out.println("size: " + size + "\ttime: " + MF.getElapsedSeconds());
            
        }
    }
    
private static class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        
        for (int i = 0; i < nums.length; i++) {
            
            int tempSum = 0;
            
            for (int j = i; j < nums.length; j++) {
                //System.out.println(i + " " + j);
                
                tempSum += nums[j];
                
                if (tempSum > max)
                    max = tempSum;
            }
        }
        return max;
    }
    
    
}


/**
     * Low inclusive max exclusive
     * @param arr
     * @param low
     * @param high
     * @return 
     */
    private int sum(int[] arr, int low, int high) {
        int sum = 0;
        for (int i = low; i < high; i++) 
            sum += arr[i];
        return sum;
    }

}
