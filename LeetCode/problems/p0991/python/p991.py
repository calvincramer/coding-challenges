#[COMPLETED]

class Solution:
    def brokenCalc(self, X: int, Y: int) -> int:
        count = 0
        while Y > X:
            Y = Y+1 if Y % 2 == 1 else Y // 2
            count += 1
        return count + (X - Y)
