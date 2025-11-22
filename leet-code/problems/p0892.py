import main


class Solution:
    def surfaceArea(self, grid: list[list[int]]) -> int:
        area = 0
        height = len(grid)
        width = len(grid[0])

        # Bottom and top faces (skip zeros)
        area += height * width * 2
        for y in range(height):
            for x in range(width):
                if grid[y][x] == 0:
                    area -= 2

        # Left / right faces
        for y in range(height):
            for x in range(width):
                # Beginning or end?
                if x == 0:
                    area += grid[y][x]
                if x == width - 1:
                    area += grid[y][x]
                # Difference with previous
                if x > 0:
                    area += abs(grid[y][x] - grid[y][x - 1])

        # Up / down faces
        for x in range(width):
            for y in range(height):
                # Beginning or end?
                if y == 0:
                    area += grid[y][x]
                if y == height - 1:
                    area += grid[y][x]
                # Difference with previous
                if y > 0:
                    area += abs(grid[y][x] - grid[y - 1][x])

        return area


test = main.UTest()
sol = Solution()
test.test_eq(sol.surfaceArea(grid=[[1, 2], [3, 4]]), 34)
test.test_eq(sol.surfaceArea(grid=[[1, 1, 1], [1, 0, 1], [1, 1, 1]]), 32)
test.test_eq(sol.surfaceArea(grid=[[2, 2, 2], [2, 1, 2], [2, 2, 2]]), 46)
test.test_eq(sol.surfaceArea(grid=[[1]]), 6)
