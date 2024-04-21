#[COMPLETED]

from typing import List

class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        zeroes = []
        for y in range(len(matrix)):
            for x in range(len(matrix[0])):
                if matrix[y][x] == 0:
                    zeroes.append( (y, x) )
        for y, x in zeroes:
            for tempy in range(len(matrix)):
                matrix[tempy][x] = 0
            for tempx in range(len(matrix[0])):
                matrix[y][tempx] = 0


sol = Solution()
grid = [
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
sol.setZeroes(grid)
for row in grid:
    print(row)
