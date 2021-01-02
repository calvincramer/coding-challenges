#[COMPLETED]

class Solution:
    def intToRoman(self, num: int) -> str:
        map = {1: "I", 5: "V", 10: "X", 50:"L", 100:"C", 500:"D", 1000:"M",
               4: "IV", 9: "IX", 40:"XL", 90:"XC", 400:"CD", 900:"CM"}
        # Try greedy
        values = sorted(map.keys())
        roman = ""
        while num > 0:
            index = 0
            while index < len(values) and values[index] <= num:
                index += 1
            index -= 1
            mult = num // values[index]
            roman = roman + (mult * map[values[index]])
            num -= mult * values[index]
        return roman


def test(inp, expect):
    sol = Solution()
    ans = sol.intToRoman(inp)
    print(ans, "expected ", expect, end="")
    if expect != ans:
        print("\tWRONG")
    else:
        print()

test(3, "III")
test(4, "IV")
test(9, "IX")
test(58, "LVIII")
test(1994, "MCMXCIV")
