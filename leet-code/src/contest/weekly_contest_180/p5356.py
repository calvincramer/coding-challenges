from typing import List

class Solution:
    def luckyNumbers(self, matrix: List[List[int]]) -> List[int]:
        result = []
        for y in range(len(matrix)):
            row = matrix[y]
            min_index_x = row.index(min(row))
            # see if it is the max in the column
            col = [matrix[dy][min_index_x] for dy in range(len(matrix))]
            max_index_y = col.index(max(col))
            if max_index_y == y:
                result.append(matrix[y][min_index_x])
        return result




sol = Solution()
matrix = [[3, 7, 8], [9, 11, 13], [15, 16, 17]]
print(sol.luckyNumbers(matrix), "expected [15]")
matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
print(sol.luckyNumbers(matrix), "expected [12]")
matrix = [[7,8],[1,2]]
print(sol.luckyNumbers(matrix), "expected [7]")
