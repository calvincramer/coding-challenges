#[COMPLETED]

from typing import List

class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        width = len(mat[0])
        height = len(mat)
        min_dim = min(width, height)
        # Left part (go from top to bottom)
        for y in range(0, len(mat) - 1):
            diag = [mat[y+i][i] for i in range(min(min_dim, height - y))]
            diag = sorted(diag)
            for i in range(len(diag)):
                mat[y + i][i] = diag[i]
        # Top part (go across in x direction
        for x in range(1, len(mat[0]) - 1):
            diag = [mat[i][x + i] for i in range(min(min_dim, width - x))]
            diag = sorted(diag)
            for i in range(len(diag)):
                mat[i][x + i] = diag[i]
        return mat

sol = Solution()
print(sol.diagonalSort(mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]), "expected [[1,1,1,1],[1,2,2,2],[1,2,3,3]]")
