#[COMPLETED]

from typing import List

class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        if not nums:
            return -1
        if len(nums) == 1:
            return 0
        # Check end points:
        if nums[0] > nums[1]:
            return 0
        if nums[-1] > nums[-2]:
            return len(nums) - 1
        # Middle
        for i in range(1, len(nums) - 1):
            if nums[i-1] < nums[i] > nums[i+1]:
                return i
        return "Impossible"


sol = Solution()
print(sol.findPeakElement([1,2,3,1]), "expected 2")
