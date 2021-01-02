#[COMPLETED]

from typing import List

class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        going_to_rot_next = []
        minutes = 0

        def find_oranges(y, x, l):
            if y > 0 and grid[y-1][x] == 1:
                l.append( (y-1,x) )
            if x > 0 and grid[y][x-1] == 1:
                l.append( (y,x-1) )
            if y + 1 < len(grid) and grid[y+1][x] == 1:
                l.append( (y+1,x) )
            if x + 1 < len(grid[0]) and grid[y][x+1] == 1:
                l.append( (y,x+1) )

        # Gather initial rot list
        for y in range(len(grid)):
            for x in range(len(grid[0])):
                if grid[y][x] == 2:
                    find_oranges(y,x, going_to_rot_next)
        # Step until nothing more to rot
        while len(going_to_rot_next) > 0:
            minutes += 1
            # rot oranges
            temp = []
            for y,x in going_to_rot_next:
                grid[y][x] = 2
                find_oranges(y,x, temp)
            going_to_rot_next = temp
            # Only keep oranges that weren't rotted this minute
            going_to_rot_next = [ (y,x) for y,x in going_to_rot_next if grid[y][x] == 1]

        # Check wether all rotted
        for row in grid:
            for n in row:
                if n == 1:
                    return -1
        return minutes


sol = Solution()
print(sol.orangesRotting([[2,1,1],[1,1,0],[0,1,1]]), "expected 4")
print(sol.orangesRotting([[2,1,1],[0,1,1],[1,0,1]]), "expected -1")
print(sol.orangesRotting([[0,2]]), "expected 0")
print(sol.orangesRotting([[2,2],[1,1],[0,0],[2,0]]), "expected 1")
