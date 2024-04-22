#[COMPLETED]

from typing import List

class Solution:
    def isMagic(self, grid, x, y):
        _sum = sum(grid[y][x:x+3])  # All sums must be equal
        nums = set()    # Hold all numbers seen so far
        # Row wise
        for trav_y in range(y, y + 3):
            temp_sum = 0
            for trav_x in range(x, x + 3):
                n = grid[trav_y][trav_x]
                if n in nums:
                    return False
                else:
                    nums.add(n)
                temp_sum += n
            if temp_sum != _sum:
                return False
        # Check all numbers found, numbers are 1 to 9
        if len(nums) != 9 or list(nums) != list(range(1, 10)):
            return False
        # Col wise
        for trav_x in range(x, x + 3):
            temp_sum = 0
            for trav_y in range(y, y + 3):
                n = grid[trav_y][trav_x]
                temp_sum += n
            if temp_sum != _sum:
                return False
        # Diagonals
        if grid[y][x] + grid[y+1][x+1] + grid[y+2][x+2] != _sum:
            return False
        if grid[y][x+2] + grid[y+1][x+1] + grid[y+2][x] != _sum:
            return False
        return True

    def numMagicSquaresInside(self, grid: List[List[int]]) -> int:
        if not grid or len(grid) == 0:
            return 0
        total = 0
        for y in range(len(grid) - 2):
            for x in range(len(grid[0]) - 2):
                if self.isMagic(grid, x, y):
                    total += 1
        return total


sol = Solution()
g = [[4,3,8,4],
     [9,5,1,9],
     [2,7,6,2]]
print(sol.numMagicSquaresInside(g), "expected 1")\

g = [[10,3,5],[1,6,11],[7,9,2]]
print(sol.numMagicSquaresInside(g), "expected 0")\
