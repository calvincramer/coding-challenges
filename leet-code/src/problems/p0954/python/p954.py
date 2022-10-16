#[COMPLETED]

# Can make solution faster by subtracting least element greadily from 2x
# that element.

from typing import List
import heapq
from collections import defaultdict

class Solution:
    def canReorderDoubled(self, A: List[int]) -> bool:
        A = [abs(n) for n in A]            # Convert to abs val
        heapq.heapify(A)
        freq = defaultdict(int)
        done = set()
        for n in A:
            freq[n] += 1
        while A:
            n = heapq.heappop(A)
            if n in done:
                continue
            if 2*n not in freq or freq[2*n] == 0:
                return False
            freq[n] -= 1
            freq[2*n] -= 1
            if freq[n] == 0:
                done.add(n)
            if freq[2*n] == 0:
                done.add(2*n)
        return True


sol = Solution()
l = [3,1,3,6]
print(sol.canReorderDoubled(l), "expected False")
l = [2,1,2,6]
print(sol.canReorderDoubled(l), "expected False")
l = [4,-2,2,-4]
print(sol.canReorderDoubled(l), "expected True")
l = [1,2,4,16,8,4]
print(sol.canReorderDoubled(l), "expected False")
