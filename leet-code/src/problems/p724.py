#[COMPLETED]

from typing import List

class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        if not nums or len(nums) == 0:
            return -1
        sum_r = sum(nums) - nums[0]
        sum_l = 0
        if sum_l == sum_r:
            return 0
        for pivot in range(1, len(nums)):
            sum_l += nums[pivot - 1]
            sum_r -= nums[pivot]
            if sum_l == sum_r:
                return pivot
        return -1


sol = Solution()
print(sol.pivotIndex([1, 7, 3, 6, 5, 6]), "expected 3")
print(sol.pivotIndex([1, 2, 3]), "expected -1")
print(sol.pivotIndex([-1,-1,-1,0,1,1]), "expected 0")
