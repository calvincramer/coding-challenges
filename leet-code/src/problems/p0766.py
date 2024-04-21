#[COMPLETED]

class Solution:
    def isToeplitzMatrix(self, matrix) -> bool:
        for start_y in range(1, len(matrix) - 1):
            end_i = min(len(matrix) - start_y, len(matrix[0]), len(matrix))
            temp_set = {matrix[i+start_y][i] for i in range(end_i)}
            #print(temp_set)
            if len(temp_set) > 1:
               return False
        for start_x in range(0, len(matrix[0]) - 1):
            end_i = min(len(matrix[0]) - start_x, len(matrix[0]), len(matrix))
            temp_set = {matrix[i][i+start_x] for i in range(end_i)}
            #print(temp_set)
            if len(temp_set) > 1:
               return False
        return True


matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
matrix2 = [
  [1,2,3,4],
  [5,6,7,8],
  [9,1,2,3]
]
matrix3 = [
  [1,2,3,4],
  [5,6,7,8],
  [9,1,2,3],
  [4,5,6,7],
  [8,9,1,2],
  [3,4,5,6],
  [7,8,9,1],
]
matrix4 = [
  [1,2],
  [2,2]
]
sol = Solution()
print(sol.isToeplitzMatrix(matrix), "expected True")
print(sol.isToeplitzMatrix(matrix4), "expected False")


