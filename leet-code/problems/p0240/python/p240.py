#[COMPLETED]

class Solution(object):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: bool
    """
    def searchMatrix(self, matrix, target):
        if matrix is None or len(matrix) == 0 or len(matrix[0]) == 0:
            return False
        col = 0
        row = 0
        while col < len(matrix[0]):
            # Walk towards target until equal to target, end of column, or greater than target
            if matrix[row][col] < target:   # Walk up
                while row < len(matrix) and matrix[row][col] < target:
                    row += 1
            else:
                while row > 0 and matrix[row][col] > target:
                    row -= 1
            if row >= len(matrix):
                col += 1
                row -= 1
            elif matrix[row][col] == target:
                return True
            else:
                col += 1
        return False


sol = Solution()
mat = \
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
print("5?: ", sol.searchMatrix(mat, 5), "expected true")
print("20?: ", sol.searchMatrix(mat, 20), "expected false")
