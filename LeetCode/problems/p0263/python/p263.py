#[COMPLETED]

class Solution:

    def isUgly(self, num):
        if num == 1:
            return True
        if num < 1:
            return False
        if num in [2,3,5]:
            return True
        if self.isPrime(num):
            return False
        primefacs = []

        for x in range(1, int(num ** 0.5) + 2):
            if num % x == 0:
                if self.isPrime(x) and x not in [2,3,5]:
                    return False
                otherFac = num // x
                if self.isPrime(otherFac) and otherFac not in [2,3,5]:
                    return False
        return True

    def isPrime(self, n):
        if n < 2:
            return False
        if n == 2:
            return True
        if n % 2 == 0:
            return False

        for i in range(3, int(n ** 0.5) + 2, 2):
            if n % i == 0:
                return False
        return True

    def printFactors(self, n):
        facs = []
        for i in range(1, n+1):
            if n % i == 0:
                facs.append(i)
        print(facs)

s = Solution()

s.isUgly(28)

for i in range(0, 29):
    if s.isUgly(i):
        print(i)

s.printFactors(28)
print(28 ** 0.5)