#[COMPLETED]

class Solution(object):
    """
    :type x: int
    :rtype: int
    """
    def reverse(self, x):
        s = str(x)
        neg = False
        if s[0] == '-':
            s = s[1:]
            neg = True
        s = s[::-1] if neg is False else '-' + s[::-1]
        x = int(s)
        if x < -2**31 or x > 2**31 - 1:
            return 0
        return x

sol = Solution()
print(sol.reverse(123), "expected 321")
print(sol.reverse(-123), "expected -321")
print(sol.reverse(120), "expected 21")
print(sol.reverse(1534236469), "expected 0")
print(sol.reverse(1563847412), "expected 0")

print(-2**31)
print(2**31 - 1)
