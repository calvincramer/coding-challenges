#[COMPLETED]

from typing import List

class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        spiral = []
        for _ in range(n):
            spiral.append([None] * n)
        # Generate
        x = 0
        y = 0
        dir = 0
        num = 1
        stop = n ** 2
        while num <= stop:
            spiral[y][x] = num
            num += 1
            # Update direction
            if dir == 0:                            # Right
                if x >= n - 1 or spiral[y][x+1]:
                    dir = 1
                    y += 1  # Go down
                else:
                    x += 1  # Go right
            elif dir == 1:                          # Down
                if y >= n - 1 or spiral[y+1][x]:
                    dir = 2
                    x -= 1  # Go left
                else:
                    y += 1  # Go down
            elif dir == 2:                          # Left
                if x < 1 or spiral[y][x-1]:
                    dir = 3
                    y -= 1   # Go up
                else:
                    x -= 1  # Go left
            elif dir == 3:                          # Up
                if y < 1 or spiral[y-1][x]:
                    dir = 0
                    x += 1  # Go right
                else:
                    y -= 1  # Go up
        return spiral


sol = Solution()
sp = sol.generateMatrix(5)
for r in sp:
    print(r)
