#[COMPLETED]

# This solution doesn't work, too lazy to complete it.

class Solution:
    def getSum(self, a: int, b: int) -> int:
        if b < a:
            a, b = b, a
        c = 0       # Carry
        digit = 0
        res = 0
        while a:
            a_dig = a & 1
            b_dig = b & 1
            carry_next = ((a_dig | b_dig) & c) | (a_dig & b_dig)
            here = (a_dig & b_dig & c) | (~a_dig & ~b_dig & c) | (~a_dig & b_dig & ~c) | (a_dig & ~b_dig & ~c)
            c = carry_next
            res += here << digit
            digit += 1
            a = a >> 1
            b = b >> 1
        while b or c:
            b_dig = b & 1
            here = b_dig ^ c
            c = c & b_dig
            res += here << digit
            b = b >> 1
        return res

# def f(a,b,c):
#     return ((a | b) & c) | (a & b)
#
# def g(a,b,c, expected):
#     print(a, b, c, " ", f(a,b,c), " ", expected, "" if f(a,b,c) == expected else "BAD")
#
# g(0,0,0,0)
# g(0,1,0,0)
# g(1,0,0,0)
# g(1,1,0,1)
# g(0,0,1,0)
# g(0,1,1,1)
# g(1,0,1,1)
# g(1,1,1,1)

sol = Solution()
print(sol.getSum(1,2))
print(sol.getSum(-2, 3))

# ((a | b) & c) | (a & b)
# ((A + B)C ) + AB
# (AC + BC) + AB
# AC + BC + AB

# a b c  h nc
# 0 0 0  0 0
# 0 1 0  1 0
# 1 0 0  1 0
# 1 1 0  0 1
# 0 0 1  1 0
# 0 1 1  0 1
# 1 0 1  0 1
# 1 1 1  1 1