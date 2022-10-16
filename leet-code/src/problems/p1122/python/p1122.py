#[COMPLETED]

from typing import List
from collections import defaultdict

class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        occ = defaultdict(int)
        for n in arr1:
            occ[n] += 1
        ret = []
        for n in arr2:
            ret.extend([n] * occ[n])
        not_found = []
        arr2_set = set(arr2)
        for n in arr1:
            if n not in arr2_set:
                not_found.append(n)
        ret.extend(sorted(not_found))
        return ret


sol = Solution()
print(sol.relativeSortArray(arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
), "expected [2,2,2,1,4,3,3,9,6,7,19]")
