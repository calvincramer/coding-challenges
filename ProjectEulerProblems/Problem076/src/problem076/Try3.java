package problem076;

import java.util.ArrayList;
import java.util.List;

public class Try3 {
    
    static int num = 2;
    static long total = 0;
    
    public static void main(String[] args) {
        
        for (; num <= 100; num++) {
            total = 0;
            for (int ways = 2; ways <= num; ways++) {
                int[] nums = new int[ways];
                nums[0] = num - ways + 1;
                for (int i = 1; i < nums.length; i++)
                    nums[i] = 1;
                computeHowMany(nums, 0);
            }
            System.out.println(num + ":\tways: " + total);
            System.out.println("");
        }
        
    }
    
    public static void computeHowMany(int[] nums, int index) {
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println("");
        
        
        if (nums.length - index == 2) {
            total += nums[index] / 2;
            return;
        }
        
        
        
        if (nums[index] == 1)
            return;
        
        nums[index] = nums[index] - 1;
        nums[index+1] = nums[index+1] + 1;
        total += 1;
        computeHowMany(nums, index + 1);
    }
    
    
    
    public static void printList(List arr) {
        System.out.print("[");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i).toString());
            if (i < arr.size() - 1)
                System.out.print(" ");
        }
        System.out.print("]");
    }
}
