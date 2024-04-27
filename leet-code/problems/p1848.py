from typing import List
from main import UTest


class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        ans = len(nums)     # Big number
        # Right
        for i in range(start, len(nums)):
            if nums[i] == target:
                ans = min(ans, abs(i - start))
                break   # won't find min after this
        # Left
        for i in range(start - 1, -1, -1):
            if nums[i] == target:
                ans = min(ans, abs(i - start))
                break   # won't find min after this
        return ans


sol = Solution()
test = UTest()
test.test_eq(sol.getMinDistance([1,2,3,4,5], 5, 3), 1)
test.test_eq(sol.getMinDistance([1], 1, 0), 0)
test.test_eq(sol.getMinDistance([1,1,1,1,1,1,1,1,1,1], 1, 0), 0)
