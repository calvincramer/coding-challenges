#[COMPLETED]

from typing import List

class Solution:
    def oddCells(self, n: int, m: int, indices: List[List[int]]) -> int:
        mat = [ [0] * m for _ in range(n) ]
        for ri, ci in indices:
            for i in range(m):
                mat[ri][i] += 1
            for i in range(n):
                mat[i][ci] += 1
        count = 0
        for row in mat:
            for n in row:
                if n % 2 == 1:
                    count += 1
        return count


sol = Solution()
print(sol.oddCells(2, 3 ,[[0,1],[1,1]]))
