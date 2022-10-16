#[COMPLETED]

from typing import List

class Solution:
    def bs_l(self, nums, target, low, high):
        lo = 0
        hi = len(nums)
        while lo < hi:
            mid = (lo + hi) // 2
            if nums[mid] >= target:
                hi = mid
            else:
                lo = mid + 1
        return lo

    def bs_r(self, nums, target, low, high):
        lo = 0
        hi = len(nums)
        while lo < hi:
            mid = (lo + hi) // 2
            if nums[mid] > target:
                hi = mid
            else:
                lo = mid + 1
        return lo

    def searchRange(self, nums: List[int], target: int) -> List[int]:
        l = self.bs_l(nums, target, 0, len(nums) - 1)
        if l == len(nums) or nums[l] != target:
            return [-1, -1]
        r = self.bs_r(nums, target, 0, len(nums) - 1) - 1
        return [l, r]


sol = Solution()
print(sol.searchRange([5,7,7,8,8,10], 8))
print(sol.searchRange([5,7,7,8,8,10], 6))
print(sol.searchRange([1], 1))
