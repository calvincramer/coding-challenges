from typing import List
from collections import deque


class Solution:
    def maxResult(self, nums: List[int], k: int) -> int:
        dq = deque([0])
        for i in range(1, len(nums)):
            nums[i] += nums[dq[0]]
            while dq and nums[dq[-1]] <= nums[i]:
                dq.pop()
            dq.append(i)
            if i - dq[0] >= k:
                dq.popleft()
        return nums[-1]


sol = Solution()
print(sol.maxResult(nums=[1, -1, -2, 4, -7, 3], k=2), "expected 7")
print(sol.maxResult(nums=[10, -5, -2, 4, 0, 3], k=3), "expected 17")
