#[COMPLETED]

from typing import List

class Solution:
    def numRookCaptures(self, board: List[List[str]]) -> int:
        rookx = -1
        rooky = -1
        for y in range(len(board)):
            for x in range(len(board[0])):
                if board[y][x] == 'R':
                    rooky = y
                    rookx = x
                    break
            if rooky != -1:
                break
        count = 0
        # Left
        for x in range(rookx - 1, -1, -1):
            if board[rooky][x] == 'B':
                break
            if board[rooky][x] == 'p':
                count += 1
                break
        # Right
        for x in range(rookx + 1, len(board[0])):
            if board[rooky][x] == 'B':
                break
            if board[rooky][x] == 'p':
                count += 1
                break
        # Up
        for y in range(rooky - 1, -1, -1):
            if board[y][rookx] == 'B':
                break
            if board[y][rookx] == 'p':
                count += 1
                break
        # Down
        for y in range(rooky + 1, len(board)):
            if board[y][rookx] == 'B':
                break
            if board[y][rookx] == 'p':
                count += 1
                break
        return count
