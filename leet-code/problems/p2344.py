#!/usr/bin/env python3

"""
1. Take GCD of numsDivide
2. If GCD is 1, then impossible to divide all nums, so return -1
3. If a number in nums divides the GCD, then this is a valid answer, return
"""

import main
from math import gcd


# def euclid(a: int, b: int) -> int:
#     while b != 0:
#         b, a = a % b, b
#     return a


class Solution:
    def minOperations(self, nums: list[int], numsDivide: list[int]) -> int:
        # Calculate GCD of entire numsDivide
        _gcd = numsDivide.pop()
        while len(numsDivide) > 0:
            _gcd = gcd(_gcd, numsDivide.pop())

        # Find divisible num
        nums = sorted(nums)
        for i, n in enumerate(nums):
            if _gcd % n == 0:
                return i
            if n > _gcd:
                break
        return -1


test = main.UTest()
sol = Solution()
test.test_eq(sol.minOperations(nums=[2, 3, 2, 4, 3], numsDivide=[9, 6, 9, 3, 15]), 2)
test.test_eq(sol.minOperations(nums=[4, 3, 6], numsDivide=[8, 2, 6, 10]), -1)
