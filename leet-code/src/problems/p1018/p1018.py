import main


class Solution:
    def prefixesDivBy5(self, nums: list[int]) -> list[bool]:
        ans = []
        n = 0
        for bit in nums:
            n = (n * 2) + (1 if bit else 0)
            ans.append(True if n % 5 == 0 else False)
        return ans


sol = Solution()
test = main.UTest()
test.test_eq(sol.prefixesDivBy5([0, 1, 1, 1, 1, 1]), [True, False, False, False, True, False])
