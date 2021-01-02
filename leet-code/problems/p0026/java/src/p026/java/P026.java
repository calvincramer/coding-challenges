//[COMPLETED]

package p026;

import mathtools.MF;

public class P026 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[] {1,1,2};
        System.out.println(s.removeDuplicates(arr));
        MF.printList(arr);
    }

    
private static class Solution {
    public int removeDuplicates(int[] nums) {
        int place = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[place])
                continue;
            place++;
            nums[place] = nums[i];
        }
        //don't need this
        for (int i = place + 1; i < nums.length; i++)
            nums[i] = 0;
        return place + 1;
    }
}
}
