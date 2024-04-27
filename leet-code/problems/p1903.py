import main


class Solution:
    def largestOddNumber(self, num: str) -> str:
        for i in range(len(num) - 1, -1, -1):
            if num[i] in "13579":
                return num[0 : i + 1]
        return ""


sol = Solution()
test = main.UTest()
test.test_eq(sol.largestOddNumber("52"), "5")
test.test_eq(sol.largestOddNumber("4206"), "")
test.test_eq(sol.largestOddNumber("35427"), "35427")
