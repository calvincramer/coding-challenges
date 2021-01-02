#[COMPLETED]

class Solution:
    def isHappy(self, n):
        if n < 1:
            return False

        seen = []
        while n != 1:
            nextNum = self.digitSquare(n)
            if nextNum in seen:
                return False
            seen.append(nextNum)
            n = nextNum
        return True

    #sums the square of the digits of n
    def digitSquare(self, n):
        sum = 0
        for d in str(n):
            sum += int(d) ** 2
        return sum


s = Solution()
print(s.isHappy(19))
print(s.digitSquare(19))