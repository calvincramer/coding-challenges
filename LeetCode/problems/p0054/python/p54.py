#[COMPLETED]

from typing import List

class Solution:
    def spiralOrder(self, matrix) -> List[int]:
        if not matrix or len(matrix) == 0:
            return None
        ret = []
        # Surround matrix for easy bounds checking
        matrix.insert(0, [None] * len(matrix[0]))
        matrix.append([None] * len(matrix[0]))
        for r_i in range(len(matrix)):
            matrix[r_i].insert(0, None)
            matrix[r_i].append(None)
        y = 1
        x = 1
        dir = 0
        while True:
            ret.append(matrix[y][x])
            matrix[y][x] = None
            # Update direction
            if dir == 0 and matrix[y][x+1] is None:
                dir = 1
            elif dir == 1 and matrix[y+1][x] is None:
                dir = 2
            elif dir == 2 and matrix[y][x-1] is None:
                dir = 3
            elif dir == 3 and matrix[y-1][x] is None:
                dir = 0
            # Step
            if dir == 0:
                x += 1
            elif dir == 1:
                y += 1
            elif dir == 2:
                x -= 1
            elif dir == 3:
                y -= 1
            # Exit
            if matrix[y][x] is None:
                return ret
        return None     # Should never get here

sol = Solution()
g = [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
print(sol.spiralOrder(g))
