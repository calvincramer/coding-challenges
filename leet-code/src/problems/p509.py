#[COMPLETED]

class Solution:
    def fib(self, N: int) -> int:
        if N <= 0:
            return 0
        if N == 1:
            return 1
        n1 = 0
        n2 = 1
        for _ in range(1, N):
            n1, n2 = n2, n1 + n2
        return n2


sol = Solution()
for i in range(0, 10):
    print(sol.fib(i))
