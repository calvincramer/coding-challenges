#[COMPLETED]

from typing import List

class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        s = sum(nums[0:k])
        max_avg = s / k
        for i in range(1, len(nums) - k + 1):
            s -= nums[i-1]
            s += nums[i-1+k]
            max_avg = max(max_avg, s / k)
        return max_avg


sol = Solution()
print(sol.findMaxAverage([1,12,-5,-6,50,3], k = 4), "expected 12.75")
print(sol.findMaxAverage([0,1,1,3,3], 4), "expected 2.0")
