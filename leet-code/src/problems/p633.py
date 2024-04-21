#[COMPLETED]

class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        if c < 0:
            return False
        sqrt_floor = int(c ** 0.5)
        for a in range(0, sqrt_floor + 1):
            b_sqr = c - (a*a)
            if b_sqr == (int(b_sqr ** 0.5)) ** 2:
                return True
        return False


sol = Solution()
for n in range(1, 100):
    print(n, "\t:", end = "")
    if sol.judgeSquareSum(n):
        print("Yes")
    else:
        print()