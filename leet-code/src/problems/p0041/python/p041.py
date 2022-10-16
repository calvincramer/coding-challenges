#[COMPLETED]

from typing import List
# Solution taken from online. Not my idea.
class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        i = 0
        lenn = len(nums)
        while i < lenn:
            if nums[i] == i+1 or 0 >= nums[i] or nums[i] > lenn:
                i += 1
            elif nums[nums[i] - 1] != nums[i]:
                # Swap i and nums[i]-1
                ind = nums[i]-1
                temp = nums[i]
                nums[i] = nums[ind]
                nums[ind] = temp
            else:
                i += 1
        i = 0
        while i < lenn and nums[i] == i+1:
            i += 1
        return i+1


sol = Solution()
print(sol.firstMissingPositive([3,4,-1,1]), "expected 2")
