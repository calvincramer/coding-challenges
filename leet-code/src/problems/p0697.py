import main

from collections import defaultdict


class Solution:
    def findShortestSubArray(self, nums: list[int]) -> int:
        info = dict()  # n -> count, first index, last index
        for i, n in enumerate(nums):
            if n not in info.keys():
                info[n] = (1, i, i)
            else:
                c, f, _ = info[n]
                info[n] = (c + 1, f, i)
        # Find smallest length sub-array with degree
        degree = max(item[0] for item in info.values())
        ans = len(nums)
        for item in info.values():
            c, f, l = item
            if c != degree:
                continue
            ans = min(ans, l - f + 1)
        return ans


test = main.UTest()
sol = Solution()
test.test_eq(sol.findShortestSubArray([1, 2, 2, 3, 1]), 2)
test.test_eq(sol.findShortestSubArray([1, 2, 2, 3, 1, 4, 2]), 6)
