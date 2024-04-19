import main

from collections import defaultdict


class Solution:
    def sumOfUnique(self, nums: list[int]) -> int:
        d = defaultdict(int)
        for n in nums:
            d[n] += 1
        s = 0
        for k, v in d.items():
            if v != 1:
                continue
            s += k
        return s


sol = Solution()
test = main.UTest()
test.test_eq(sol.sumOfUnique([1, 2, 3, 2]), 4)
test.test_eq(sol.sumOfUnique([1, 1, 1, 1, 1]), 0)
test.test_eq(sol.sumOfUnique([1, 2, 3, 4, 5]), 15)
