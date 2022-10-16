from typing import List

import main


class SubrectangleQueries:
    def __init__(self, rectangle: List[List[int]]):
        self._r = rectangle

    def updateSubrectangle(self, row1: int, col1: int, row2: int, col2: int, newValue: int) -> None:
        for r in range(row1, row2+1):
            for c in range(col1, col2+1):
                self._r[r][c] = newValue

    def getValue(self, row: int, col: int) -> int:
        return self._r[row][col]


# Your SubrectangleQueries object will be instantiated and called as such:
obj = SubrectangleQueries([[1,2,1],[4,3,4],[3,2,1],[1,1,1]])
test = main.UTest()
test.test_eq(obj.getValue(0, 2), 1)
obj.updateSubrectangle(0,0,3,2,5)
test.test_eq(obj.getValue(0, 2), 5)
test.test_eq(obj.getValue(3, 1), 5)
obj.updateSubrectangle(3,0,3,2,10)
test.test_eq(obj.getValue(3, 1), 10)
test.test_eq(obj.getValue(0, 2), 5)
