#[COMPLETED]

from main import UTest


class Solution:
    def generateParenthesis(self, n: int):
        # Credit: https://leetcode.com/problems/generate-parentheses/discuss/10369/Clean-Python-DP-Solution
        dp = [[] for _ in range(n+1)]
        dp[0].append('')
        for i in range(n+1):
            for j in range(i):
                dp[i] += [f'({x}){y}' for x in dp[j] for y in dp[i-j-1]]
        return dp[n]


test = UTest()
sol = Solution()
test.test_eq(sol.generateParenthesis(3), ["((()))", "(()())", "(())()", "()(())", "()()()"])
