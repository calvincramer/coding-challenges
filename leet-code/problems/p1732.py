#!/usr/bin/env python3

"""
Go through each point in gain from i=0 to end n
Keep track of elevation, update using point in gain
Keep maximum seen
"""

import main


class Solution:
    def largestAltitude(self, gain: list[int]) -> int:
        largest = 0
        elevation = 0
        for g in gain:
            elevation += g
            largest = max(largest, elevation)
        return largest


test = main.UTest()
sol = Solution()
test.test_eq(sol.largestAltitude([-5, 1, 5, 0, -7]), 1)
test.test_eq(sol.largestAltitude([-4, -3, -2, -1, 4, 3, 2]), 0)
