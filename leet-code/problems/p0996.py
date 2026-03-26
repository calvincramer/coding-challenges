from main import UTest

import itertools


class Solution:
    def numSquarefulPerms(self, nums: list[int]) -> int:
        total = 0

        is_square = {}
        for a in range(len(nums)):
            for b in range(len(nums)):
                if a == b:
                    continue
                val = nums[a] + nums[b]
                is_square[val] = (int(val**0.5)) ** 2 == val

        print(is_square)

        for perm in set(itertools.permutations(nums)):
            good = True
            for i in range(len(nums) - 1):
                if is_square[perm[i] + perm[i + 1]] == False:
                    good = False
                    break
            if good:
                total += 1
        return total


UTest.exp_eq(Solution().numSquarefulPerms([1, 17, 8]), 2)
UTest.exp_eq(Solution().numSquarefulPerms([2, 2, 2]), 1)
UTest.exp_eq(Solution().numSquarefulPerms([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]), 0)
