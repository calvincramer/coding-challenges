#[COMPLETED]

from typing import List

class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        k = len(nums) - 2
        l = k + 1
        while k >= 0:
            if nums[k] < nums[k+1]:
                break
            k -= 1
        if k < 0:
            nums.reverse()
            return
        while l > k:
            if nums[k] < nums[l]:
                break
            l -= 1
        temp = nums[k]
        nums[k] = nums[l]
        nums[l] = temp
        nums[k+1:] = nums[k+1:][::-1]

sol = Solution()
l = [3,2,1]
sol.nextPermutation(l)
print(l)