#[COMPLETED]

dict = { "I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000 }

class Solution:
    def romanToInt(self, s: str) -> int:
        num = 0
        for i in range(len(s) - 1):
            c = s[i]
            n = s[i+1]
            if c == "I" and (n == "V" or n == "X"):
                num -= 1
            elif c == "X" and (n == "L" or n == "C"):
                num -= 10
            elif c == "C" and (n == "D" or n == "M"):
                num -= 100
            else:
                num += dict[c]
        return num + dict[s[-1]]

sol = Solution()
print(sol.romanToInt("XIV"))
print(sol.romanToInt("MCMXCIV"), "expected 1994")
