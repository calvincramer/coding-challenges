from main import UTest


class Solution:
    def luckyNumbers(self, matrix: list[list[int]]) -> list[int]:
        min_rows = [min(row) for row in matrix]
        max_cols = [
            max(matrix[row_idx][col_idx] for row_idx in range(len(matrix))) for col_idx in range(len(matrix[0]))
        ]
        return list(set(min_rows).intersection(set(max_cols)))


UTest.exp_eq(Solution().luckyNumbers(matrix=[[3, 7, 8], [9, 11, 13], [15, 16, 17]]), [15])
