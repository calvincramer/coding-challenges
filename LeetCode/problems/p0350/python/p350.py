#[COMPLETED]

from typing import List
from collections import defaultdict

class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums2_dict = defaultdict(int)
        for n in nums2:
            nums2_dict[n] += 1

        inter = []
        for n in nums1:
            if n in nums2_dict and nums2_dict[n] > 0:
                inter.append(n)
                nums2_dict[n] -= 1
        return inter

sol = Solution()
print(sol.intersect([1,2,2,1], [2]))
