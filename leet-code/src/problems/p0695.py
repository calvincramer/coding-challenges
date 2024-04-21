#[COMPLETED]

from typing import List

class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        maxArea = 0

        def areaOfIsland(_y, _x) -> int:
            if _y < 0 or _x < 0 or _y >= len(grid) or _x >= len(grid[_y]):
                return 0
            if grid[_y][_x] == 0:
                return 0
            grid[_y][_x] = 0  # Erase as we go
            return 1 + areaOfIsland(_y - 1, _x) \
                     + areaOfIsland(_y + 1, _x) \
                     + areaOfIsland(_y, _x - 1) \
                     + areaOfIsland(_y, _x + 1)

        for y in range(len(grid)):
            for x in range(len(grid[y])):
                if grid[y][x] == 1:
                    maxArea = max(maxArea, areaOfIsland(y, x))
        return maxArea


grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

sol = Solution()
print(sol.maxAreaOfIsland(grid))
