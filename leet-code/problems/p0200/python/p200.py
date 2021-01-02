#[COMPLETED]

from typing import List

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        num_islands = 0
        for y in range(len(grid)):
            for x in range(len(grid[0])):
                if grid[y][x] == '1':
                    num_islands += 1
                    self.trav_island(grid, y, x)
        return num_islands

    def trav_island(self, grid, y, x):
        if y < 0 or x < 0 or y >= len(grid) or x >= len(grid[0]):
            return
        if grid[y][x] == '1':
            grid[y][x] = '0'
        else:
            return
        self.trav_island(grid, y - 1, x)
        self.trav_island(grid, y + 1, x)
        self.trav_island(grid, y, x - 1)
        self.trav_island(grid, y, x + 1)

