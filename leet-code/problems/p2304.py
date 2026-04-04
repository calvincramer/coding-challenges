from main import UTest


class Solution:
    def minPathCost(self, grid: list[list[int]], moveCost: list[list[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        pathCost = [[0 for _ in range(n)] for _ in range(m)]

        # First row
        for j in range(n):
            pathCost[0][j] = grid[0][j]

        # Subsequent rows
        for i in range(1, m):
            for j in range(n):
                pathCost[i][j] = grid[i][j] + min(pathCost[i - 1][k] + moveCost[grid[i - 1][k]][j] for k in range(n))

        # Min cost of last row
        return min(pathCost[m - 1][k] for k in range(n))


UTest.exp_eq(
    Solution().minPathCost(
        grid=[[5, 3], [4, 0], [2, 1]], moveCost=[[9, 8], [1, 5], [10, 12], [18, 6], [2, 4], [14, 3]]
    ),
    17,
)
UTest.exp_eq(
    Solution().minPathCost(
        grid=[[5, 1, 2], [4, 0, 3]],
        moveCost=[[12, 10, 15], [20, 23, 8], [21, 7, 1], [8, 1, 13], [9, 10, 25], [5, 3, 2]],
    ),
    6,
)
