#[COMPLETED]

class Solution:
    def longestValidParentheses(self, s: str) -> int:
        # This is given dp solution, not my own.
        dp = [0] * len(s)
        maxans = 0
        for i in range(1, len(s)):
            if s[i] == '(':
                continue
            if s[i-1] == '(':
                dp[i] = (dp[i-2] if i >= 2 else 0) + 2
            elif i - dp[i-1] > 0 and s[i - dp[i-1] - 1] == '(':
                dp[i] = dp[i-1] + 2 + (dp[i - dp[i-1] - 2] if i - dp[i-1] >= 2 else 0)
            maxans = max(maxans, dp[i])
        return maxans

sol = Solution()
print(sol.longestValidParentheses("(()"), "expected 2")
print(sol.longestValidParentheses(")()())"), "expected 4")
print(sol.longestValidParentheses("()(())"), "expected 6")
