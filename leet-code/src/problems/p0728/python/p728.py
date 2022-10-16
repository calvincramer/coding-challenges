#[COMPLETED]

class Solution:
    def selfDividingNumbers(self, left, right):
        l = []
        for n in range(left, right+1):
            if self.isSelfDividing(n):
                l.append(n)
        return l

    def isSelfDividing(self, n):
        for d in str(n):
            if int(d) == 0:
                return False
            if n % int(d) != 0:
                return False
        return True

s = Solution()
print(s.selfDividingNumbers(1,22))