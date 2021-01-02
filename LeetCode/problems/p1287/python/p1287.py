#[COMPLETED]

from typing import List
from collections import defaultdict

class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        occ = defaultdict(int)
        quarter_lenn = len(arr) / 4
        for n in arr:
            occ[n] += 1
        for n in occ:
            if occ[n] > quarter_lenn:
                return n
        return None


sol = Solution()
print(sol.findSpecialInteger([1,2,2,6,6,6,6,7,10]), "expected 6")
