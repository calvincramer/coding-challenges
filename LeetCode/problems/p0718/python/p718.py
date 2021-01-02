#[COMPLETED]

from typing import List

class Solution:
    def findLength(self, A: List[int], B: List[int]) -> int:
        tbl = [ [0] * (len(A) + 1) for _ in range(len(B) + 1)]
        for x in range(len(A) - 1, -1, -1):
            for y in range(len(B) - 1, -1, -1):
                if A[x] == B[y]:
                    tbl[x][y] = tbl[x+1][y+1] + 1
        return max(max(row) for row in tbl)


sol = Solution()
print(sol.findLength([1,2,3,2,1], [3,2,1,4,7]))
