#[COMPLETED]

from typing import List
from main import UTest

class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        def DFS(queens, xy_diff, xy_sum):
            p = len(queens)
            if p == n:
                result.append(queens)
                return
            for q in range(n):
                if q not in queens and p-q not in xy_diff and p+q not in xy_sum:
                    DFS(queens+[q], xy_diff+[p-q], xy_sum+[p+q])
        result = []
        DFS([], [], [])
        return [ ['.'*i + 'Q' + '.'*(n-i-1) for i in sol] for sol in result]


test = UTest()
sol = Solution()
ans4 = sol.solveNQueens(4)
test.test_true(len(ans4) == 2)
test.test_eq(ans4, [ [".Q..", "...Q", "Q...", "..Q."], ["..Q.", "Q...", "...Q", ".Q.."] ])
