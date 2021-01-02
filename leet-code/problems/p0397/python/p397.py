#[COMPLETED]

# Can do memoized version in order to make faster

class Solution:
    def integerReplacement(self, n: int) -> int:
        if n <= 1:
            return 0
        elif n % 2 == 0:
            return 1 + self.integerReplacement(n / 2)
        else:
            return 1 + min(self.integerReplacement(n+1), self.integerReplacement(n-1))


sol = Solution()
print(sol.integerReplacement(8), "expected 3")
print(sol.integerReplacement(7), "expected 4")