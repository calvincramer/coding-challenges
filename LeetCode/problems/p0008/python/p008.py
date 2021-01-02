#[COMPLETED]

class Solution:
    def myAtoi(self, s) -> int:
        pass
        # Find first +, -, or number
        # Parse number until end of string or whitespace
        if s == "":
            return 0
        i = 0
        while i < len(s) and s[i] != '+' and s[i] != '-' and s[i] == ' ':
            i += 1
        mult = 1
        if i >= len(s):
            return 0
        if s[i] == '+':
            i += 1
        elif s[i] == '-':
            mult = -1
            i += 1
        elif not s[i].isdigit():
            return 0
        num = 0
        while i < len(s) and s[i].isdigit():
            num += int(s[i]) - int('0')
            i += 1
            num *= 10
        ans = mult * (num // 10)
        if ans > 2**31 - 1:
            ans = 2**31 - 1
        elif ans < -2**31:
            ans = -2**31
        return ans

sol = Solution()
print(sol.myAtoi("  123"), "expected 123")
print(sol.myAtoi("   +123"), "expected 123")
print(sol.myAtoi("   -42"), "expected -42")
print(sol.myAtoi("3.14159"), "expected 3")
print(sol.myAtoi("+"), "expected 0")
print(sol.myAtoi("42"), "expected 42")
print(sol.myAtoi("-91283472332"), "expected -2147483648")
