#[COMPLETED]

class Solution(object):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: bool
    """
    def searchMatrix(self, matrix, target):
        if matrix is None or len(matrix) == 0:
            return False
        # Binary search but convert row col indices to linear indices
        # linear index l to row col is l |-> [l / rowLength][l % rowLength]
        return self.bs_trav(matrix, target, 0, len(matrix) * len(matrix[0]) - 1)

    def bs_trav(self, matrix, target, low, high):
        if low > high:
            return False
        mid = (low + high) // 2
        mid_val = matrix[mid // len(matrix[0])][mid % len(matrix[0])]
        if mid_val == target:
            return True
        elif mid_val < target:  # take high
            return self.bs_trav(matrix, target, mid + 1, high)
        else:   # take low
            return self.bs_trav(matrix, target, low, mid - 1)


sol = Solution()
matrix = \
[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
print("3?", sol.searchMatrix(matrix, 3), "expected true")
print("4?", sol.searchMatrix(matrix, 4), "expected false")
print("-5?", sol.searchMatrix(matrix, -5), "expected false")
print("16?", sol.searchMatrix(matrix, 16), "expected true")
print("50?", sol.searchMatrix(matrix, 50), "expected true")
print("51?", sol.searchMatrix(matrix, 51), "expected false")