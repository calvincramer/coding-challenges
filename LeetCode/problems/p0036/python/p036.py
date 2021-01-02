#[COMPLETED]

class Solution(object):
    # Only checks for duplicates
    # Don't need to check that all numbers are in the board, since if there are no duplicates at the end of the
    # algorithm, then the board is properly filled
    # Don't call this every time a number is set.
    """
    :type board: List[List[str]]
    :rtype: bool
    """
    def isValidSudoku(self, board):
        # Check cols
        for col in range(0, 9):
            d = {}
            for row in range(0, 9):
                if board[row][col] == '.':
                    continue
                elif board[row][col] in d:
                    return False
                else:
                    d[board[row][col]] = None
        # Check rows
        for row in range(0, 9):
            d = {}
            for col in range(0, 9):
                if board[row][col] == '.':
                    continue
                elif board[row][col] in d:
                    return False
                else:
                    d[board[row][col]] = None
        # Check 3x3s
        for y in range(0, 9, 3):
            for x in range(0, 9, 3):
                d = {}
                for dy in range(0, 3):
                    for dx in range(0, 3):
                        if board[y+dy][x+dx] == '.':
                            continue
                        elif board[y+dy][x+dx] in d:
                            return False
                        else:
                            d[board[y+dy][x+dx]] = None
        return True

b1 = [
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
b2 = [
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]

sol = Solution()
print(sol.isValidSudoku(b1), "expected true")
print(sol.isValidSudoku(b2), "expected false")