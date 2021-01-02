//[COMPLETED]

package p027;

import mathtools.MF;

public class P027 {

    public static void main(String[] args) {
        int[] arr = new int[] {};
        Solution s = new Solution();
        
        System.out.println(s.removeElement(arr, 2));
        
        MF.printList(arr);
        
    }

    
private static class Solution {
    public int removeElement(int[] nums, int val) {
        
        //if (nums == null || nums.length == 0)
        //    return 0;
        
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val)
                continue;
            
            nums[j] = nums[i];
            j++;
        }
        
        //dont need
        for (int i = j; i < nums.length; i++)
            nums[i] = 0;
        
        return j;
    }
}

}
