#[COMPLETED]

from typing import List

class Solution:
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        ret = []
        for i in range(0, len(nums), 2):
            a = nums[i]
            b = nums[i+1]
            ret.extend([b] * a)
        return ret


sol = Solution()
print(sol.decompressRLElist([1,2,3,4]))
