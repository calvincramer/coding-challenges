#[COMPLETED]

class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        if len(num2) < len(num1):
            num1, num2 = num2, num1
        c = 0
        res = ""
        zero = ord('0')
        for d1, d2 in zip(reversed(num1), reversed(num2)):
            here = c + ord(d1) - zero + ord(d2) - zero
            c = here // 10
            here = here % 10
            res = chr(here + zero) + res
        for d in reversed(num2[0:-len(num1)]):
            here = c + ord(d) - zero
            c = here // 10
            here = here % 10
            res = chr(here + zero) + res
        if c > 0:
            res = chr(c + zero) + res
        return res


sol = Solution()
print(sol.addStrings("1234", "5678"), "expected ", str(1234+5678))
print(sol.addStrings("1000", "2"), "expected ", str(1000+2))
print(sol.addStrings("1", "9"), "expected ", str(1+9))
print(sol.addStrings("9", "99"), "expected ", str(99+9))
