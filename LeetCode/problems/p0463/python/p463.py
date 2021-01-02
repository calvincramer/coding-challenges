#[COMPLETED]

class Solution:
    def islandPerimeter(self, grid) -> int:
        c = 0   # Count
        h = len(grid)
        w = len(grid[0])
        for y in range(h):
            for x in range(w):
                if grid[y][x]:
                    # Up
                    if y == 0 or grid[y-1][x] == False:
                        c += 1
                    # Down
                    if y == h - 1 or grid[y+1][x] == False:
                        c += 1
                    # Left
                    if x == 0 or grid[y][x-1] == False:
                        c += 1
                    # Right
                    if x == w - 1 or grid[y][x+1] == False:
                        c += 1
        return c


sol = Solution()
print(sol.islandPerimeter([[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]), " expected 16")
