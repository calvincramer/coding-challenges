from main import UTest


class Solution:

    def longestPalindromeSubseq(self, s: str) -> int:
        self.s = s
        self.n = len(s)
        self.memo = [[None for _ in range(self.n)] for _ in range(self.n)]
        return self.foo(left=0, right=self.n - 1)

    def foo(self, left: int, right: int) -> int:
        # Bases
        if left > right:
            return 0
        if left == right:
            return 1

        # Recurse
        if self.memo[left][right] != None:
            return self.memo[left][right]

        if self.s[left] == self.s[right]:
            # Match
            if self.memo[left + 1][right - 1] != None:
                ans = 2 + self.memo[left + 1][right - 1]
            else:
                ans = 2 + self.foo(left + 1, right - 1)
        else:
            # No match
            if self.memo[left][right - 1] != None:
                take_left = self.memo[left][right - 1]
            else:
                take_left = self.foo(left, right - 1)

            if self.memo[left + 1][right] != None:
                take_right = self.memo[left + 1][right]
            else:
                take_right = self.foo(left + 1, right)

            ans = max(take_left, take_right)

        self.memo[left][right] = ans
        return ans


UTest.exp_eq(Solution().longestPalindromeSubseq("bbbab"), 4)
UTest.exp_eq(Solution().longestPalindromeSubseq("cbbd"), 2)
UTest.exp_eq(Solution().longestPalindromeSubseq("abaababaab"), 9)
