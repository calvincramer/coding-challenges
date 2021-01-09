//[COMPLETED]

package p035;

public class P035 {

    public static void main(String[] args) {
        int[] arr= new int[] {1,3,5,6};
        Solution s = new Solution();
        System.out.println(s.searchInsert(arr, 5));
        System.out.println(s.searchInsert(arr, 2));
        System.out.println(s.searchInsert(arr, 7));
        System.out.println(s.searchInsert(arr, 0));
        
        
        System.out.println("");
        arr = new int[] {1,3};
        System.out.println(s.searchInsert(arr, 4));
    }


private static class Solution {
    public int searchInsert(int[] nums, int target) {
        return this.searchInsert(nums, target, 0, nums.length);
    }
    
    private int searchInsert(int[] nums, int target, int low, int high) {
        if (low >= high) {
            if (low < nums.length) {
                if (target == nums[low]) return low;
                else if (target < nums[low]) return low;
                else return ++low;
            } 
            else
                return low;
        }
        
        int middle = low + (high - low) / 2;
        
        if (target > nums[middle]) {
            return searchInsert(nums, target, middle + 1, high);
        }
        else if (target < nums[middle])
            return searchInsert(nums, target, low, middle - 1);
        else
            return middle;      //target == nums[middle]
    }
}

}
