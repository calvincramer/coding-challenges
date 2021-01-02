#[COMPLETED]

import time

class Solution(object):
    """
    :type board: List[List[str]]
    :rtype: None Do not return anything, modify board in-place instead.
    """
    def solveSudoku(self, board):
        # Find set places
        d = {}
        for y in range(0, 9):
            for x in range(0, 9):
                if board[y][x] != '.':
                    d[(y, x)] = None
        self.trav(board, 0, 0, d)

    def trav(self, board, y, x, set_places):
        if y >= len(board):
            return True
        elif (y, x) in set_places:
            return self.trav(board, y + (1 if x + 1 >= len(board[0]) else 0), (x + 1) % len(board[0]), set_places)

        for n in range(1,10):
            board[y][x] = str(n)
            if self.check_one_spot(board, y, x):
                res = self.trav(board, y + (1 if x + 1 >= len(board[0]) else 0), (x + 1) % len(board[0]), set_places)
                if res:
                    return True
        # Bad path
        board[y][x] = '.'
        return False

    # does good_boards job, but only for a specified location
    def check_one_spot(self, board, y, x):
        # Check row
        d = set()
        for col in range(0,9):
            val = board[y][col]
            if val == '.':
                continue
            elif val in d:
                return False
            else:
                d.add(val)
        # Check col
        d = set()
        for row in range(0,9):
            val = board[row][x]
            if val == '.':
                continue
            elif val in d:
                return False
            else:
                d.add(val)
        # Check 3x3
        startY = y - (y % 3)
        startX = x - (x % 3)
        d = set()
        for dy in range(0,3):
            for dx in range(0,3):
                val = board[startY + dy][startX + dx]
                if val == '.':
                    continue
                elif board[startY + dy][startX + dx] in d:
                    return False
                else:
                    d.add(board[startY + dy][startX + dx])
        return True

    # Only checks for duplicates
    # Don't need to check that all numbers are in the board, since if there are no duplicates at the end of the
    # algorithm, then the board is properly filled
    # Don't call this every time a number is set.
    def good_board(self, board):
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


def print_board(board):
    for row_i,row in enumerate(board):
        if row_i % 3 == 0:
            print("+-----------+")
        for i in range(0,9):
            if i % 3 == 0:
                print("|", end="")
            print(row[i], end="")
        print("|")
    print("+-----------+")


def compare_boards(b1, b2):
    for y in range(0, 9):
        for x in range(0, 9):
            if b1[y][x] != b2[y][x]:
                print("y=",y,"x=",x,"\tb1[y][x]=", b1[y][x], "\tb2[y][x]=",b2[y][x])
                return False
    return True


board = [
    ["5", "3", ".", ".", "7", ".", ".", ".", "."],
    ["6", ".", ".", "1", "9", "5", ".", ".", "."],
    [".", "9", "8", ".", ".", ".", ".", "6", "."],
    ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
    ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
    ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
    [".", "6", ".", ".", ".", ".", "2", "8", "."],
    [".", ".", ".", "4", "1", "9", ".", ".", "5"],
    [".", ".", ".", ".", "8", ".", ".", "7", "9"],
]
expected = [
    ["5", "3", "4", "6", "7", "8", "9", "1", "2"],
    ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
    ["1", "9", "8", "3", "4", "2", "5", "6", "7"],
    ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
    ["4", "2", "6", "8", "5", "3", "7", "9", "1"],
    ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
    ["9", "6", "1", "5", "3", "7", "2", "8", "4"],
    ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
    ["3", "4", "5", "2", "8", "6", "1", "7", "9"],
]
sol = Solution()
print("Inital board:")
print_board(board)

start = time.time()
sol.solveSudoku(board)
elapsed = time.time() - start
print("\nAfter solved:")
print_board(board)
print("equal? : ", compare_boards(board, expected))
print("time: ", elapsed)

board2 = [["1",".",".",".","7",".",".","3","."],["8","3",".","6",".",".",".",".","."],[".",".","2","9",".",".","6",".","8"],["6",".",".",".",".","4","9",".","7"],[".","9",".",".",".",".",".","5","."],["3",".","7","5",".",".",".",".","4"],["2",".","3",".",".","9","1",".","."],[".",".",".",".",".","2",".","4","3"],[".","4",".",".","8",".",".",".","9"]]
print("\n\nBoard 2:")
print_board(board2)
start = time.time()
sol.solveSudoku(board2)
elapsed = time.time() - start
print("\nSolved:")
print_board(board2)
print("time: ", elapsed)

#1.233597755432129