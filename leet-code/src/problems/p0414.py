#[COMPLETED]

from typing import List

class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        maxes = [float("-inf")] * 3
        nums = set(nums)        # Skip duplicates
        for n in nums:
            if n > maxes[0]:
                maxes.insert(0, n)
                maxes.pop(-1)
            elif n > maxes[1]:
                maxes.insert(1, n)
                maxes.pop(-1)
            elif n > maxes[2]:
                maxes[2] = n
        return maxes[2] if maxes[2] != float("-inf") else maxes[0]


sol = Solution()
print(sol.thirdMax([2,2,3,1]), " expected 1")
